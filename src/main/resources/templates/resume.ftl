<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<style>
  @page {
    size: A4;
    margin: 0;
  }
  * { margin: 0; padding: 0; box-sizing: border-box; }
  body {
    font-family: 'PingFang SC', 'Microsoft YaHei', 'SimSun', sans-serif;
    color: #333;
    font-size: 10.5pt;
    line-height: 1.5;
  }
  .page {
    width: 210mm;
    padding: 20mm 18mm 15mm;
  }
  
  /* Header */
  .header {
    text-align: center;
    margin-bottom: 12px;
    padding-bottom: 10px;
    border-bottom: 2px solid #2c5f8a;
  }
  .header .name {
    font-size: 22pt;
    font-weight: 700;
    color: #1a3a5c;
  }
  .header .title {
    font-size: 11pt;
    color: #555;
    margin-top: 3px;
  }
  .header .contact {
    font-size: 9pt;
    color: #666;
    margin-top: 6px;
    display: flex;
    justify-content: center;
    gap: 18px;
    flex-wrap: wrap;
  }
  .header .contact span { white-space: nowrap; }

  /* Section */
  .section {
    margin-bottom: 8px;
  }
  .section-title {
    font-size: 11pt;
    font-weight: 700;
    color: #2c5f8a;
    margin-bottom: 4px;
    padding-bottom: 1px;
    border-bottom: 1px solid #d0d8e0;
  }

  /* Personal Advantage */
  .advantage-list { list-style: none; padding: 0; margin: 0; }
  .advantage-list li {
    font-size: 10pt;
    line-height: 1.6;
    padding-left: 12px;
    position: relative;
    margin-bottom: 1px;
    color: #444;
  }
  .advantage-list li::before { content: "▸"; position: absolute; left: 0; color: #2c5f8a; font-weight: bold; }

  /* Job */
  .job { margin-bottom: 8px; }
  .job-header { display: flex; justify-content: space-between; align-items: baseline; }
  .job-header .company { font-size: 10.5pt; font-weight: 600; color: #1a3a5c; }
  .job-header .period { font-size: 9pt; color: #888; }
  .job .role { font-size: 9.5pt; color: #666; margin-bottom: 2px; }
  .job ul { list-style: none; padding: 0; margin: 0; }
  .job ul li {
    font-size: 9.5pt;
    line-height: 1.55;
    padding-left: 12px;
    position: relative;
    margin-bottom: 1px;
    color: #444;
  }
  .job ul li::before { content: "▸"; position: absolute; left: 0; color: #2c5f8a; }

  /* Project */
  .project { margin-bottom: 7px; }
  .project-header { display: flex; justify-content: space-between; align-items: baseline; }
  .project-header .name { font-size: 10.5pt; font-weight: 600; color: #1a3a5c; }
  .project-header .period { font-size: 9pt; color: #888; }
  .project .role { font-size: 9.5pt; color: #666; margin-bottom: 1px; }
  .project .desc { font-size: 9.5pt; line-height: 1.5; color: #444; margin-bottom: 2px; }
  .project .tech { font-size: 9pt; color: #2c5f8a; margin-bottom: 2px; }
  .project ul { list-style: none; padding: 0; margin: 0; }
  .project ul li {
    font-size: 9.5pt;
    line-height: 1.55;
    padding-left: 12px;
    position: relative;
    margin-bottom: 1px;
    color: #444;
  }
  .project ul li::before { content: "▸"; position: absolute; left: 0; color: #2c5f8a; }

  .flag { font-size: 8.5pt; color: #2c5f8a; border: 1px solid #2c5f8a; padding: 0 5px; border-radius: 2px; margin-left: 4px; }

  /* Tags */
  .tags { margin-top: 1px; margin-bottom: 4px; }
  .tag { display: inline-block; font-size: 8.5pt; color: #2c5f8a; border: 1px solid #b0c4d8; padding: 0 6px; border-radius: 2px; margin: 1px 1px; }

  /* Edu */
  .edu-item { margin-bottom: 4px; }
  .edu-item .school { font-size: 10pt; font-weight: 500; color: #333; }
  .edu-item .detail { font-size: 9pt; color: #555; }
  .edu-item .period { font-size: 8.5pt; color: #888; }

  .break-inside { page-break-inside: avoid; }
</style>
</head>
<body>
<div class="page">
  <!-- Header -->
  <div class="header">
    <div class="name">${name}</div>
    <div class="title">${title}</div>
    <div class="contact">
      <span>📞 ${phone}</span>
      <span>✉️ ${email}</span>
      <#if location??><span>📍 ${location}</span></#if>
    </div>
  </div>

  <!-- 个人优势 -->
  <div class="section">
    <div class="section-title">个人优势</div>
    <ul class="advantage-list">
      <#list advantages as adv>
      <li>${adv}</li>
      </#list>
    </ul>
  </div>

  <!-- 技术栈 -->
  <div class="section">
    <div class="section-title">技术栈</div>
    <div class="tags">
      <#list skills as skill>
      <span class="tag">${skill}</span>
      </#list>
    </div>
  </div>

  <!-- 工作经历 -->
  <div class="section">
    <div class="section-title">工作经历</div>
    <#list jobs as job>
    <div class="job break-inside">
      <div class="job-header">
        <span class="company">${job.company}</span>
        <span class="period">${job.period}</span>
      </div>
      <div class="role">${job.role}</div>
      <ul>
        <#list job.details as detail>
        <li>${detail}</li>
        </#list>
      </ul>
    </div>
    </#list>
  </div>

  <!-- 项目经历 -->
  <div class="section">
    <div class="section-title">项目经历</div>
    <#list projects as proj>
    <div class="project break-inside">
      <div class="project-header">
        <span class="name">${proj.name}</span>
        <span class="period">${proj.period}</span>
      </div>
      <div class="role">${proj.role}</div>
      <div class="desc">${proj.desc}</div>
      <div class="tech"><b>技术栈：</b>${proj.tech}</div>
      <ul>
        <#list proj.details as detail>
        <li>${detail}</li>
        </#list>
      </ul>
    </div>
    </#list>
  </div>

  <!-- 教育背景 -->
  <div class="section">
    <div class="section-title">教育背景</div>
    <#list education as edu>
    <div class="edu-item">
      <div class="school">${edu.school}</div>
      <div class="detail">${edu.degree} · ${edu.major}</div>
      <div class="period">${edu.period}</div>
    </div>
    </#list>
  </div>
</div>
</body>
</html>
