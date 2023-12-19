function validateForm() {
    var pid = document.getElementById("pid").value;
    var pname = document.getElementById("pname").value;
    var pcost = document.getElementById("pcost").value;
    var pmadein = document.getElementById("pmadein").value;
    var company = document.getElementById("company").value;

    if (pid === "" || pname === "" || pcost === "" || pmadein === "" || company === "") {
        alert("Please enter all details");
        return false; // Prevent form submission
    }

    return true; // Allow form submission
}