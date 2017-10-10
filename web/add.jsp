
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
     <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- Website CSS style -->
        <link rel="stylesheet" type="text/css" href="assets/css/login.css">

        <!-- Website Font style -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

        <!-- Google Fonts -->
        <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
        <title>Login</title>
  </head>

  <body>

    <div class="container">
      <div class="col-xs-12 col-sm-12 col-md-10">
          <h1><a>Add a new book</a></h1>
          <form action="/EditSevlet" method="post">
              <fieldset class="scheduler-border">
                  <input type="hidden" name="id" value="${book.id}" />
                  <div class="form-group">
                      <label class="control-label">Name (*)</label>
                      <input type="text" class="form-control" placeholder="Name" name="name" value="${book.name}"required="true"/>
                  </div>
                  <div class="form-group">
                      <label class="control-label">Author (*)</label>
                      <input type="text" class="form-control" placeholder="Author" name="author" value="${book.author}"required="true"/>
                  </div>
                  

                  <div class="form-group">
                      <label class="control-label">Category (*)</label>
                      <select class="form-control" name="categoryID">
                          
                          <option value="0">--- Select ---</option>
                          <c:forEach var="categoryItem" items="${categoryList}" >
                              <c:if test="${categoryItem.id == book.category.id}">
                                <option value="${categoryItem.id}" selected> ${categoryItem.name}</option>
                              </c:if>
                              <c:if test="${categoryItem.id != book.category.id}">
                                <option value="${categoryItem.id}">${categoryItem.name}</option>
                              </c:if>
                          </c:forEach>
                        </select>
                  </div>
                  <br>
                  
                  <button type="submit" class="btn btn-info" value="Add">Save</button>
              </fieldset>
          </form:form>
      </div>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
