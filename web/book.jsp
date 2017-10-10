<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book</title>

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Website CSS style -->
        <link rel="stylesheet" type="text/css" href="assets/css/book.css">
        <link rel="stylesheet" type="text/css" href="assets/css/search.css">

    </head>
    <body>
        <form action="/Search" method ="get">
            <div class="container">
                <div class="row">

                    <div id="custom-search-input">
                        <div class="input-group col-md-12">
                            <input type="text" class="  search-query form-control" placeholder="Search" name="search" />
                            <span class="input-group-btn">
                                <button class="btn btn-danger" type="button">
                                    <span class=" glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <br></br>
        <div class="container">
            <div class="row col-md-6 col-md-offset-2 custyle">
                <c:if test="${data.size() > 0}"> 
                    <table class="table table-striped custab">
                        <thead>
                        <a href="/EditSevlet?isAdd=true" class="btn btn-primary btn-xs pull-right"><b>+</b> Add a new book</a>
                        <tr>
                            <th>ID</th>
                            <th>Book name</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th class="text-center">Action</th>
                        </tr>
                        </thead>
                        <c:forEach var="book" items="${data}">

                            <tr>
                                <td>${book.id} </td>
                                <td>${book.name}</td>
                                <td>${book.author}</td>
                                <td>${book.category.name}</td>
                                <td class="text-center"><a class='btn btn-info btn-xs' href="/EditSevlet?id=${book.id}"><span class="glyphicon glyphicon-edit"></span> Edit</a> <a href="/DeleteBookServlet?id=${book.id}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${data.size() == 0}"> 
                    <h1>No data</h1>
                </c:if>
            </div>
        </div>
    </body>
</html>
