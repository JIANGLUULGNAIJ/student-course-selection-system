<%@ page import="java.util.List" %>
<%@ page import="pers.jl.pojo.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	ArrayList<Student> Studentlist = (ArrayList<Student>) request.getAttribute("Studentlist");
	System.out.println(Studentlist);
	int sum = Studentlist.size();//记录总数
	String strNumber=request.getParameter("pageNumber");

	int number = 0;//待显示的页码，默认为第一页
	if(strNumber==null||strNumber.equals("0")){//表明在QueryString中没有pageNum这一个参数，此时显示第 //一页的数据
		number=1;
	}else{
		number = Integer.parseInt(strNumber);//取的待显示页码，将字符串转换成整数

	}
	int count = 5;//每页显示的条数
	int maxPage;//最大页数

	if(sum%5==0){//一页显示的记录数，目前设计为5条
		maxPage = sum/5;//最多页数，能整除的，结果为页数
	}else{
		maxPage = sum/5+1;//不能整除的，结果加1
	}
	int start = (number-1)*count;//开始记录数
	int end = number*count;//结束记录数
	if(end>sum-1){
		end = sum;//防止越界
	}
	request.setAttribute("sum",sum);
	request.setAttribute("number",number);
	request.setAttribute("maxPage",maxPage);
	request.setAttribute("count",count);
	request.setAttribute("list",Studentlist);

	for(int i = start; i<end; i++){

		Student stu = (Student)Studentlist.get(i);

	}

%>
<html>
	<head>
		<meta charset="utf-8">
		<title>列表</title>
		<link  rel="stylesheet"  href="${basePath}static/css/styles.css" />
		<link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$('.remove').click(function(){
					if(confirm("确定要删除吗？")){
						window.location.href="${basePath}student?method=delete&id="+$(this).attr("keyword");
					}
				})
			})
		</script>
	</head>
	<body>
		<form action="${basePath}student?method=list" method="post">
			<div class="condition">
				ID：<input type="text" name="stuId" value="${student.stuId}">
				姓名：<input type="text" name="stuName" value="${student.stuName}">
				学号：<input type="text" name="stuNo" value="${student.stuNo}">
				<button>
					<i class="fa fa-search"></i>
					查询
				</button>
				<button type="button" onclick="window.location.href='page/student/add.jsp'">
					<i class="fa fa-plus"></i>
					新增
				</button>
			</div>
		</form>
		<form action="${basePath}student?method=list" id="tableList" method="post">
		<input type="hidden" name="pageNo" value="${pageInfo.pageNo}">
		<input type="hidden" name="stuId" value="${student.stuId}">
		<input type="hidden" name="stuName" value="${student.stuName}">
		<input type="hidden" name="stuNo" value="${student.stuNo}">
		<table class="tablelist">
			<thead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>学号</th>
					<th width="120px">操作</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="student">
			<tr>
				<td>${student.stuId}</td>
				<td>${student.stuName}</td>
				<td>${student.stuNo}</td>
				<td>
					<button class="edit" type="button" onclick="window.location.href='${basePath}student?method=edit&id=${student.stuId}'">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove" type="button" keyword="${student.stuId}">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
			</c:forEach>
		</table>
			<table class="page">
				<td>
					<button type="button" key="1">首页</button>
					<button type="button" <c:if test="${pageInfo.firstPage == true}">disabled</c:if> key="${pageInfo.prePage}">上一页</button>
					<button type="button" <c:if test="${pageInfo.lastPage == true}">disabled</c:if>  key="${pageInfo.nextPage}">下一页</button>
					<button type="button" key="${maxPage}">尾页</button>
					<input  type="text"  class="page-no"  id="page-no" value="${pageInfo.pageNo}" />
					<button type="button" class="zhuan">转</button>
					总记录条数${sum}条，当前${number}/${maxPage}页  每页${count}条数据
				</td>
			</table>
			<script type="text/javascript">
				$(function(){
					$('.page .zhuan').click(function(){
						if(parseInt($('#page-no').val())<=parseInt("${pageInfo.totalPage}")){
							$('input[name="pageNo"]').val($('#page-no').val());
							$("#tableList").submit();
						}else{
							alert("不能大于总页数");
						}
					});
					$('.page button:not(.zhuan)').click(function(){
						$('input[name="pageNo"]').val($(this).attr("key"));
						$("#tableList").submit();
					})
				})
			</script>
		</form>
	</body>
</html>
