# 📄 Resume PDF Generator

**FreeMarker + iText 7 HTML转PDF — Java简历生成工具**

> 一个纯 Java 的简历 PDF 生成器，通过 FreeMarker 模板引擎渲染 HTML，再使用 iText 7 + html2pdf 将 HTML 转换为美观的 PDF 简历文件。

---

## ✨ 特性

- **模板驱动** — FreeMarker 模板渲染，简历内容和样式分离
- **高质量 PDF** — 基于 iText 7 html2pdf，支持完整 CSS 样式
- **中文支持** — 通过 FontProvider 注册中文字体，完美渲染中文
- **纯 Java 工具** — 无需浏览器环境，一行命令生成 PDF

## 🚀 使用方法

```bash
# 编译
mvn clean package -DskipTests

# 运行
java -jar target/resume-pdf-1.0.0.jar

# 输出: 梁阿龙-Java开发工程师-简历.pdf
```

## 🏗️ 技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Java | 17 | 开发语言 |
| FreeMarker | 2.3.33 | 模板引擎 |
| iText 7 Core | 7.2.6 | PDF 生成基础库 |
| iText 7 pdfHTML | 5.0.2 | HTML → PDF 转换 |
| Maven | 3.6+ | 构建工具 |

## 📁 项目结构

```
resume-pdf/
├── pom.xml
└── src/main/
    ├── java/
    │   └── ResumePdfGenerator.java    # 核心生成器
    └── resources/
        └── templates/
            └── resume.ftl             # FreeMarker 模板
```

## 📝 License

MIT
