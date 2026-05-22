import freemarker.template.*;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.geom.PageSize;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ResumePdfGenerator {

    public static void main(String[] args) throws Exception {
        String outputDir = System.getProperty("user.home") + "/Desktop";
        String outputPath = outputDir + "/梁阿龙-Java开发工程师-简历.pdf";

        // 1. 准备数据模型
        Map<String, Object> data = buildDataModel();

        // 2. FreeMarker 渲染 HTML
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_33);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassLoaderForTemplateLoading(
            ResumePdfGenerator.class.getClassLoader(), "/templates"
        );
        Template template = cfg.getTemplate("resume.ftl");

        StringWriter htmlOut = new StringWriter();
        template.process(data, htmlOut);
        String html = htmlOut.toString();

        // 3. HTML → PDF
        try (FileOutputStream fos = new FileOutputStream(outputPath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdfDoc = new PdfDocument(writer)) {
            pdfDoc.setDefaultPageSize(PageSize.A4);
            // 留出页边距
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDoc, PageSize.A4, true);
            document.setMargins(0, 0, 0, 0);

            HtmlConverter.convertToPdf(html, pdfDoc,
                new com.itextpdf.html2pdf.ConverterProperties()
                    .setBaseUri(""));
        }

        System.out.println("✅ PDF generated: " + outputPath);
    }

    static Map<String, Object> buildDataModel() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("name", "梁阿龙");
        map.put("title", "Java开发工程师 · 4年经验");
        map.put("phone", "面试约谈");
        map.put("email", "liangalong2021@163.com");
        map.put("location", "期望城市：西安");

        // ===== 个人优势 =====
        map.put("advantages", Arrays.asList(
            "丰富的分布式架构及仓储自动化系统开发经验，熟练掌握 WES/WCS 系统架构，具备良好的编码规范与开发习惯，责任心强，对交付质量高度负责。",
            "较强的需求分析与系统设计能力，能够独立进行复杂问题的逻辑分析与解决方案设计，确保系统高效稳定。",
            "具备海外项目交付经验，曾赴日本现场进行系统部署与联调，能够快速适应国际化工作环境。",
            "优秀的沟通、表达与团队协作能力，具备良好的跨部门协作经验，能快速融入团队并推动项目进展。",
            "抗压能力强，能够适应加班和出差，始终保持高度的工作热情和执行力，乐于面对新挑战。"
        ));

        // ===== 技术栈 =====
        map.put("skills", Arrays.asList(
            "Java", "Spring Boot", "Spring Cloud", "MyBatis Plus",
            "Nacos", "Feign", "Zuul", "xxl-job",
            "MySQL", "Oracle", "ClickHouse", "达梦", "MongoDB",
            "Redis", "Kafka", "RocketMQ", "ElasticSearch",
            "WebSocket", "DataX", "Nginx", "Docker",
            "Jenkins", "Git", "Maven", "Linux",
            "JavaScript", "jQuery", "Ajax", "ECharts",
            "Vue3", "ElementUI", "小诺", "若依",
            "帆软", "OpenClaw"
        ));

        // ===== 工作经历 =====
        List<Map<String, Object>> jobs = new ArrayList<>();

        // Job 1: 菜鸟网络
        Map<String, Object> job1 = new LinkedHashMap<>();
        job1.put("company", "菜鸟网络");
        job1.put("period", "2025.02 - 至今");
        job1.put("role", "Java开发工程师 · 仓储自动化方向");
        job1.put("details", Arrays.asList(
            "参与 WES（仓储执行系统）与 WCS（仓储控制系统）核心模块开发，负责任务调度、流程编排等模块的设计与实现。",
            "对接自动化设备层，实现输送线、AGV、四穿车、提升机、机械臂等多种自动化设备的控制逻辑与调度管理。",
            "负责项目现场的设备联调与技术支持，快速响应并解决仓储现场各类技术问题，保障系统上线稳定运行。",
            "参与日本联合利华海外仓储项目的系统部署与现场实施，赴日完成联调交付工作。"
        ));
        jobs.add(job1);

        // Job 2: 热力工程
        Map<String, Object> job2 = new LinkedHashMap<>();
        job2.put("company", "北京市热力工程设计有限责任公司");
        job2.put("period", "2022.06 - 2024.12");
        job2.put("role", "Java开发工程师");
        job2.put("details", Arrays.asList(
            "负责后端性能优化，有效解决线上技术问题，保障系统稳定运行，持续提升系统响应速度与并发处理能力。",
            "主动跟踪最新技术动态，推动团队技术选型与架构升级，引入 ElasticSearch、ClickHouse 等组件提升数据处理能力。",
            "管理 SSL 证书申请与配置，强化系统安全防护，保障数据传输安全。"
        ));
        jobs.add(job2);

        // Job 3: 外企科技
        Map<String, Object> job3 = new LinkedHashMap<>();
        job3.put("company", "北京外企科技有限公司");
        job3.put("period", "2021.04 - 2022.04");
        job3.put("role", "Java开发工程师");
        job3.put("details", Arrays.asList(
            "参与需求分析、数据库设计及项目架构设计，确保技术方案合理性与可扩展性。",
            "优化产品模块结构、业务流程与逻辑，提升系统性能与可维护性。",
            "负责业务需求变更评审及接口开发与文档编写，确保接口规范清晰、易于集成。"
        ));
        jobs.add(job3);

        map.put("jobs", jobs);

        // ===== 项目经历 =====
        List<Map<String, Object>> projects = new ArrayList<>();

        // Project 1
        Map<String, Object> p1 = new LinkedHashMap<>();
        p1.put("name", "日本联合利华仓储自动化项目");
        p1.put("period", "2024.08 - 至今");
        p1.put("role", "Java开发工程师 · 赴日交付");
        p1.put("desc", "菜鸟网络承接的日本联合利华海外仓储自动化项目，负责 WES/WCS 系统的部署实施与二次开发，支持日本仓的自动化运营。项目涉及输送线、AGV、四穿车、提升机、机械臂等多类型自动化设备的联调与控制。");
        p1.put("tech", "Spring Cloud, Nacos, Redis, Kafka, ElasticSearch, WebSocket, DataX, OpenClaw");
        p1.put("details", Arrays.asList(
            "负责 WES 任务调度、波次管理、设备任务分配等核心模块开发，保障仓储作业的高效执行。",
            "实现 WCS 对输送线、AGV、四穿车、提升机等设备的控制逻辑与路径调度，确保多设备协同运行。",
            "赴日本现场完成系统部署、设备联调及上线支持，保障项目按期交付。",
            "对接联合利华 WMS 系统，实现仓储作业全链路闭环。"
        ));
        projects.add(p1);

        // Project 2
        Map<String, Object> p2 = new LinkedHashMap<>();
        p2.put("name", "沧州四星仓储自动化项目");
        p2.put("period", "2025.02 - 2025.08");
        p2.put("role", "Java开发工程师");
        p2.put("desc", "菜鸟网络沧州四星仓，主导 WES 与 WCS 开发与集成，实现自动化立体库的智能调度与控制，具备跨仓复制经验。");
        p2.put("tech", "Spring Cloud, Nacos, Redis, Kafka, ElasticSearch, WebSocket, OpenClaw");
        p2.put("details", Arrays.asList(
            "负责 WES 任务调度、流程编排等核心模块开发，实现仓储作业流程的灵活配置。",
            "实现 WCS 对输送线、AGV、四穿车、提升机等自动化设备的控制逻辑与调度管理。",
            "通过 WES 与 WCS 深度协同，构建高效稳定的仓储执行控制体系，支撑高并发场景下的自动化立体库稳定运行。",
            "形成可复制的仓储自动化解决方案，具备跨仓复制与快速部署经验。"
        ));
        projects.add(p2);

        // Project 3
        Map<String, Object> p3 = new LinkedHashMap<>();
        p3.put("name", "北京市智能供热服务平台");
        p3.put("period", "2023.04 - 2024.11");
        p3.put("role", "Java开发工程师");
        p3.put("desc", "整合北京市供热系统，涵盖碳排放管理、室温监测、数据中心、接诉即办等核心模块。室温模块对接全市200万台设备，管理百亿级室温数据，系统 QPS/TPS 约1000。");
        p3.put("tech", "Spring Cloud, Nacos, MyBatis Plus, Redis, Kafka, ElasticSearch, Nginx, ClickHouse, DataX, WebSocket");
        p3.put("details", Arrays.asList(
            "负责室温监测模块的全流程开发：需求分析、数据库设计、全市设备对接、后端 API 开发。",
            "实现报表导出、数据分析、数据报警及异常提醒等核心功能，支撑供热监管决策。",
            "Nginx 负载均衡策略配置优化，Kafka 与 ElasticSearch 集群搭建与维护。",
            "接诉即办工单处理及流转功能开发，使用 Redis 分布式锁保障并发安全。"
        ));
        projects.add(p3);

        // Project 4
        Map<String, Object> p4 = new LinkedHashMap<>();
        p4.put("name", "北京市供热信息统计系统");
        p4.put("period", "2022.09 - 2023.03");
        p4.put("role", "Java开发工程师（项目负责人）");
        p4.put("desc", "为北京市统计局提供数据填报与统计分析系统，在原有统计功能基础上增加视频培训、批量导出、AI 客服等功能模块。");
        p4.put("tech", "Spring Boot, MyBatis Plus, MySQL, Vue3, ElementUI, WebSocket, MongoDB");
        p4.put("details", Arrays.asList(
            "带领团队重构统计平台，负责需求对接、技术方案设计与项目排期管理。",
            "负责前后端核心代码编写、生产环境搭建及项目部署上线。",
            "负责用户操作培训及客户对接沟通，确保系统平稳交付。"
        ));
        projects.add(p4);

        // Project 5
        Map<String, Object> p5 = new LinkedHashMap<>();
        p5.put("name", "长通物流平台");
        p5.put("period", "2021.12 - 2022.05");
        p5.put("role", "Java开发工程师");
        p5.put("desc", "物流综合平台，涵盖在线下单、路径规划、轨迹追踪、订单搜索、支付与积分系统、抢单等功能，采用微服务架构设计。");
        p5.put("tech", "Spring Boot, Redis, RocketMQ, MongoDB, ElasticSearch");
        p5.put("details", Arrays.asList(
            "负责支付模块与积分模块开发，使用 RocketMQ 处理分布式事务，保障资金安全。",
            "负责抢单模块开发，基于 Redis 分布式锁实现高并发场景下的抢单逻辑。",
            "参与系统架构设计，解决分布式事务一致性问题。"
        ));
        projects.add(p5);

        // Project 6
        Map<String, Object> p6 = new LinkedHashMap<>();
        p6.put("name", "欣然办公系统");
        p6.put("period", "2021.07 - 2021.12");
        p6.put("role", "Java开发工程师");
        p6.put("desc", "企业内部办公系统，包含企业门户平台、人力资源管理、系统设置等核心模块，提升企业内部管理效率。");
        p6.put("tech", "Spring Boot, MyBatis, MySQL");
        p6.put("details", Arrays.asList(
            "负责后台代码编写及需求文档撰写。",
            "负责公司投票模块与人力资源管理模块的开发。"
        ));
        projects.add(p6);

        // Project 7
        Map<String, Object> p7 = new LinkedHashMap<>();
        p7.put("name", "四方管理系统");
        p7.put("period", "2021.04 - 2021.07");
        p7.put("role", "Java开发工程师");
        p7.put("desc", "企业内部管理系统，融合简单财务管理功能，提供请假审批、客户关系管理、自动提醒等功能，提升企业办公效率。");
        p7.put("tech", "Spring Boot, MyBatis, MySQL");
        p7.put("details", Arrays.asList(
            "参与需求文档讨论、功能模块划分及数据库设计。",
            "负责客户基本信息管理、员工管理及报表功能模块的开发。"
        ));
        projects.add(p7);

        map.put("projects", projects);

        // ===== 教育背景 =====
        List<Map<String, Object>> edu = new ArrayList<>();
        Map<String, Object> edu1 = new LinkedHashMap<>();
        edu1.put("school", "天津理工大学");
        edu1.put("degree", "本科");
        edu1.put("major", "计算机科学与技术");
        edu1.put("period", "2024 - 2026");
        edu.add(edu1);

        Map<String, Object> edu2 = new LinkedHashMap<>();
        edu2.put("school", "天津滨海职业学院");
        edu2.put("degree", "大专");
        edu2.put("major", "计算机科学与技术");
        edu2.put("period", "2018 - 2021");
        edu.add(edu2);

        map.put("education", edu);

        return map;
    }
}
