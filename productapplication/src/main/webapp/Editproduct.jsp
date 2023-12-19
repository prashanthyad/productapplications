<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="bg-warning">
    
    <div class="container mt-5 text-center">

		<h2 class="text-center font-italic mb-5">Updating  product Data...</h2>

    	<form method="post" action="./UpdateProductServlet" enctype="multipart/form-data" onsubmit="return validateForm()">
        
            <div class="form-group">
                <label class="font-italic font-weight-bold" for="proId">Product ID:</label>
                <input type="text" class="form-control-sm" id="proId" value="${Existingproduct.proId}" name="proId" required>
            </div>

            <div class="form-group">
                <label class="font-italic font-weight-bold"  for="proName">Product Name:</label>
                <input type="text" class="form-control-sm" id="proName" value="${Existingproduct.proName}" name="proName" required>
            </div>

            <div class="form-group">
                <label class="font-italic font-weight-bold" for="proPrice">Price:</label>
                <input type="number" class="form-control-sm" id="proPrice" value="${Existingproduct.proPrice}" name="proPrice" step="0.01" required>
            </div>

            <div class="form-group">
                <label class="font-italic font-weight-bold" for="proBrand">Brand:</label>
                <input type="text" class="form-control-sm" id="proBrand" value="${Existingproduct.proBrand}" name="proBrand" required>
            </div>

            <div class="form-group">
                <label class="font-italic font-weight-bold"  for="proMadeIn">Made In:</label>
                <input type="text" class="form-control-sm" id="proMadeIn" value="${Existingproduct.proMadeIn}" name="proMadeIn" required>
            </div>

            <div class="form-group">
                <label class="font-italic font-weight-bold"   for="proMfgDate">Manufacturing Date:</label>
                <input type="date" class="form-control-sm" id="proMfgDate" value="${Existingproduct.proMfgDate}" name="proMfgDate" required>
            </div>

            <div class="form-group">
                <label class="font-italic font-weight-bold"   for="proExpDate">Expiry Date:</label>
                <input type="date" class="form-control-sm" id="proExpDate" value="${Existingproduct.proExpDate}" name="proExpDate" required>
            </div>

            <div class="form-group">
                <label class="font-italic font-weight-bold" for="proImage">Product Image:</label>
                <input type="file" class="form-control-file-sm" id="proImage" value="${Existingproduct.proImage}"  name="proImage" accept="image/*" required>
            </div>

            <button type="submit" class="btn btn-primary">Update Product</button>
            
        </form>
    </div>
</body>
</html>