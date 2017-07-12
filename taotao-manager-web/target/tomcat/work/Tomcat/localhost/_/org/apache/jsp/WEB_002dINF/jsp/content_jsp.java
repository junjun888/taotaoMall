/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-07-12 13:49:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class content_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<div class=\"easyui-panel\" title=\"Nested Panel\" data-options=\"width:'100%',minHeight:500,noheader:true,border:false\" style=\"padding:10px;\">\r\n");
      out.write("    <div class=\"easyui-layout\" data-options=\"fit:true\">\r\n");
      out.write("        <div data-options=\"region:'west',split:false\" style=\"width:250px;padding:5px\">\r\n");
      out.write("            <ul id=\"contentCategoryTree\" class=\"easyui-tree\" data-options=\"url:'/content/category/list',animate: true,method : 'GET'\">\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div data-options=\"region:'center'\" style=\"padding:5px\">\r\n");
      out.write("            <table class=\"easyui-datagrid\" id=\"contentList\" data-options=\"toolbar:contentListToolbar,singleSelect:false,collapsible:true,pagination:true,method:'get',pageSize:20,url:'/content/query/list',queryParams:{categoryId:0}\">\r\n");
      out.write("\t\t    <thead>\r\n");
      out.write("\t\t        <tr>\r\n");
      out.write("\t\t            <th data-options=\"field:'id',width:30\">ID</th>\r\n");
      out.write("\t\t            <th data-options=\"field:'title',width:120\">内容标题</th>\r\n");
      out.write("\t\t            <th data-options=\"field:'subTitle',width:100\">内容子标题</th>\r\n");
      out.write("\t\t            <th data-options=\"field:'titleDesc',width:120\">内容描述</th>\r\n");
      out.write("\t\t            <th data-options=\"field:'url',width:60,align:'center',formatter:TAOTAO.formatUrl\">内容连接</th>\r\n");
      out.write("\t\t            <th data-options=\"field:'pic',width:50,align:'center',formatter:TAOTAO.formatUrl\">图片</th>\r\n");
      out.write("\t\t            <th data-options=\"field:'pic2',width:50,align:'center',formatter:TAOTAO.formatUrl\">图片2</th>\r\n");
      out.write("\t\t            <th data-options=\"field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime\">创建日期</th>\r\n");
      out.write("\t\t            <th data-options=\"field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime\">更新日期</th>\r\n");
      out.write("\t\t        </tr>\r\n");
      out.write("\t\t    </thead>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\tvar tree = $(\"#contentCategoryTree\");\r\n");
      out.write("\tvar datagrid = $(\"#contentList\");\r\n");
      out.write("\ttree.tree({\r\n");
      out.write("\t\tonClick : function(node){\r\n");
      out.write("\t\t\tif(tree.tree(\"isLeaf\",node.target)){\r\n");
      out.write("\t\t\t\tdatagrid.datagrid('reload', {\r\n");
      out.write("\t\t\t\t\tcategoryId :node.id\r\n");
      out.write("\t\t        });\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("var contentListToolbar = [{\r\n");
      out.write("    text:'新增',\r\n");
      out.write("    iconCls:'icon-add',\r\n");
      out.write("    handler:function(){\r\n");
      out.write("    \tvar node = $(\"#contentCategoryTree\").tree(\"getSelected\");\r\n");
      out.write("    \tif(!node || !$(\"#contentCategoryTree\").tree(\"isLeaf\",node.target)){\r\n");
      out.write("    \t\t$.messager.alert('提示','新增内容必须选择一个内容分类!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("    \tTT.createWindow({\r\n");
      out.write("\t\t\turl : \"/content-add\"\r\n");
      out.write("\t\t}); \r\n");
      out.write("    }\r\n");
      out.write("},{\r\n");
      out.write("    text:'编辑',\r\n");
      out.write("    iconCls:'icon-edit',\r\n");
      out.write("    handler:function(){\r\n");
      out.write("    \tvar ids = TT.getSelectionsIds(\"#contentList\");\r\n");
      out.write("    \tif(ids.length == 0){\r\n");
      out.write("    \t\t$.messager.alert('提示','必须选择一个内容才能编辑!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("    \tif(ids.indexOf(',') > 0){\r\n");
      out.write("    \t\t$.messager.alert('提示','只能选择一个内容!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("\t\tTT.createWindow({\r\n");
      out.write("\t\t\turl : \"/content-edit\",\r\n");
      out.write("\t\t\tonLoad : function(){\r\n");
      out.write("\t\t\t\tvar data = $(\"#contentList\").datagrid(\"getSelections\")[0];\r\n");
      out.write("\t\t\t\t$(\"#contentEditForm\").form(\"load\",data);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t// 实现图片\r\n");
      out.write("\t\t\t\tif(data.pic){\r\n");
      out.write("\t\t\t\t\t$(\"#contentEditForm [name=pic]\").after(\"<a href='\"+data.pic+\"' target='_blank'><img src='\"+data.pic+\"' width='80' height='50'/></a>\");\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(data.pic2){\r\n");
      out.write("\t\t\t\t\t$(\"#contentEditForm [name=pic2]\").after(\"<a href='\"+data.pic2+\"' target='_blank'><img src='\"+data.pic2+\"' width='80' height='50'/></a>\");\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tcontentEditEditor.html(data.content);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});    \t\r\n");
      out.write("    }\r\n");
      out.write("},{\r\n");
      out.write("    text:'删除',\r\n");
      out.write("    iconCls:'icon-cancel',\r\n");
      out.write("    handler:function(){\r\n");
      out.write("    \tvar ids = TT.getSelectionsIds(\"#contentList\");\r\n");
      out.write("    \tif(ids.length == 0){\r\n");
      out.write("    \t\t$.messager.alert('提示','未选中商品!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("    \t$.messager.confirm('确认','确定删除ID为 '+ids+' 的内容吗？',function(r){\r\n");
      out.write("    \t    if (r){\r\n");
      out.write("    \t    \tvar params = {\"ids\":ids};\r\n");
      out.write("            \t$.post(\"/content/delete\",params, function(data){\r\n");
      out.write("        \t\t\tif(data.status == 200){\r\n");
      out.write("        \t\t\t\t$.messager.alert('提示','删除内容成功!',undefined,function(){\r\n");
      out.write("        \t\t\t\t\t$(\"#contentList\").datagrid(\"reload\");\r\n");
      out.write("        \t\t\t\t});\r\n");
      out.write("        \t\t\t}\r\n");
      out.write("        \t\t});\r\n");
      out.write("    \t    }\r\n");
      out.write("    \t});\r\n");
      out.write("    }\r\n");
      out.write("}];\r\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
