package com.lilei.router_compiler;


import com.google.auto.service.AutoService;
import com.lilei.router_anno.RouteNode;
import com.lilei.router_compiler.utils.CollectionsUtil;
import com.lilei.router_compiler.utils.Constant;
import com.lilei.router_compiler.utils.FileUtils;
import com.lilei.router_compiler.utils.Logger;
import com.lilei.router_compiler.wrapper.Node;
import com.lilei.router_compiler.wrapper.NodeType;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static com.lilei.router_compiler.utils.CharUtils.firstCharUpperCase;
import static com.lilei.router_compiler.utils.Constant.ANNOTATION_TYPE_ROUTE_NODE;
import static com.lilei.router_compiler.utils.Constant.KEY_HOST_NAME;
import static com.lilei.router_compiler.utils.Constant.ROUTERTABLE;

@AutoService(Processor.class)
@SupportedOptions(KEY_HOST_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({ANNOTATION_TYPE_ROUTE_NODE})
public class BeeRouterProcessor extends AbstractProcessor{

    private Filer mFiler;
    private Elements mElementUtils;
    private Elements elements;
    private Logger logger;
    private String host = null;

    private List<Node> routeNodes = new ArrayList<Node>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
        elements = processingEnv.getElementUtils();
        mElementUtils = processingEnv.getElementUtils();
        logger = new Logger(processingEnvironment.getMessager());
        /**
         * init host
         */
        Map<String, String> options = processingEnv.getOptions();
        if (CollectionsUtil.isNotEmptyMap(options)) {
            host = options.get(KEY_HOST_NAME);
            logger.info(">>> host is " + host + " <<<");
        }
        if (host == null || host.equals("")) {
            logger.error("These no host name, at 'build.gradle', like :\n" +
                    "        javaCompileOptions {\n" +
                    "            annotationProcessorOptions {\n" +
                    "                arguments = [host: \"app\"]\n" +
                    "            }\n" +
                    "        }");
            throw new RuntimeException("BeeRouter::Compiler >>> No host name, for more information, look at gradle log.");
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> routeNodesRaw = roundEnvironment.getElementsAnnotatedWith(RouteNode.class);
        for (Element nodeRaw : routeNodesRaw) {
            logger.info("route node ----> "+nodeRaw.getSimpleName());
            RouteNode routeAnno = nodeRaw.getAnnotation(RouteNode.class);
            logger.info("route node ----> path:"+routeAnno.path()+"    desc:"+routeAnno.desc());

            Node node = new Node();
            node.setNodeType(NodeType.ACTIVITY);
            node.setTarget(nodeRaw);
            node.setHost(host);
            node.setPath(routeAnno.path());
            node.setDesc(routeAnno.desc());
            node.setPriority(routeAnno.priority());
            routeNodes.add(node);
        }
        generateRouterTable(routeNodes);
        generateRouteNode(routeNodes);
        return true;
    }

    private void generateRouterTable(List<Node> routeNodes) {
        // ./UIRouterTable/{$host}RouterTable.txt
        String fileName = "./UIRouterTable/" + firstCharUpperCase(host) + ROUTERTABLE + ".txt";
        if (FileUtils.createFile(fileName)) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("auto generated, do not change !!!! \n\n");
            stringBuilder.append("HOST : ").append(host).append("\n\n");
            stringBuilder.append("path:"+"\t"+"desc:"+"\n\n");
            for (Node node : routeNodes) {
                stringBuilder.append("BeeComp://").append(host).append(node.getPath()).append("\t\t");
                stringBuilder.append(node.getDesc()).append("\n");
                stringBuilder.append("\n");
            }
            FileUtils.writeStringToFile(fileName, stringBuilder.toString(), false);
        }
        logger.info("generating RouterTable is over");
    }

    private void generateRouteNode(List<Node> routeNodes) {
        logger.info("start to generate java file");
        String packageName = Constant.GEN_PKG;
        // superClassName
        ClassName superClass = ClassName.get(elements.getTypeElement(Constant.BaseCompRouter_ADDRESS));
        String className = firstCharUpperCase(host)+"$Router";
        MethodSpec.Builder initMapBuilder = MethodSpec
                .methodBuilder("initMap")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addCode("super.initMap();\n");
        for (Node node :routeNodes) {
            initMapBuilder.addCode("routeMapper.put($S,$T.class);\n",node.getPath(),node.getTarget());
        }
        MethodSpec initMap = initMapBuilder.build();

        MethodSpec getHost = MethodSpec
                .methodBuilder("getHost")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addStatement("return $S", host)
                .build();

        TypeSpec hello = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .superclass(superClass)
                .addMethod(initMap)
                .addMethod(getHost)
                .build();

        JavaFile file = JavaFile.builder(packageName, hello).build();
        try {
            file.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

