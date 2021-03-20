
var userId;
var addressIdEdit;
$(document).ready(function(){
    $("#addAddress").click(function (){
        qoshish();
    });
    $("#deleteUser").click(function (){
        deleteUser(userId);
    })


    //CREATE
    $("#saveUser").click(function(){
        var firstName= $("#firstName").val();
        var lastName=  $("#lastName").val();
        var age=$("#age").val();
        var selectedVal = "";
        var selected = $("input[type='radio'][name='gender']:checked");
        if (selected.length > 0) {
            selectedVal = selected.val();
        }
        var addressId=$("#tuman").val();
        var contactId=$("#contactId").val();
        var user_data={
            "firstname": firstName,
            "lastname" : lastName,
            "age"      : parseInt(age),
            "gender"   : selectedVal,
            "addressId" : parseInt(addressId),
            "contactId" : parseInt(contactId),
        }
        $.ajax('http://localhost:8080/user/upload' ,{
            data       : JSON.stringify(user_data),
            contentType: 'application/json',
            type       : 'POST',
            dataType   : 'json'
        });
    });

    //READ
    $.getJSON("http://localhost:8080/user/get?page=0",function(result){
        $.each(result.content,function (i ,data) {
            console.log(data.name)
            var id=data.id;
            var edit="<button  data-toggle=\"modal\" data-target=\"#myModal2\"  class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"2919592.svg\" onclick='editAddress("+id+")'  ></button>"
            var del=" <button  data-toggle=\"modal\" data-target=\"#myModal1\"   class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"del.png\" onclick='idUser("+id+")' ></button> "
         //   console.log(data.id);
            var gende="";
            if (data.gender){gende="Erkak"}else {
                gende="Ayol"
            }
            var tr_users="<tr id='"+id+"'>"+
                "<td>"+data.id+"</td>" +
                "<td>"+ data.firstname + "</td>" +
                "<td>"+ data.lastname + "</td>" +
                "<td>"+ data.age + "</td>" +
                "<td>"+gende+"</td>" +
                "<td>"+ data.contact.phoneNumber + "</td>" +
                "<td>"+ data.contact.email + "</td>" +
                "<td>"+ data.contact.webPage + "</td>" +
                "<td>"+ data.contact.telegramAddress + "</td>" +
                "<td>"+ data.address.streetName + "</td>" +
                "<td>"+ data.address.code + "</td>" +
                "<td>"+ data.address.district.name + "</td>" +
                "<td>"+ data.address.district.region.name + "</td>" +
                "<td>"+ edit + "</td>" +
                "<td>"+ del + "</td>"+"<tr>";
            $('#usersData').append(tr_users);

        });
    });

    $.getJSON("http://localhost:8080/address?pages=0",function(result){
        $.each(result.object.content,function (i ,data) {
            var tuma= "<option  value = '"+data.id+"' > "+ data.streetName +" "+data.code+"</option>"
            $("#tumanA").append(tuma)
        })
    })

});
//DELETE
function  deleteUser(idDelite){
    $.getJSON('http://localhost:8080/user/delete/'+ parseInt(idDelite),function (result){});
}
//UPDATE
function  editAddress(id)
{
    $.getJSON("http://localhost:8080/user/get?page=0",function(result){
        $.each(result.content,function (i ,data){
            if (id===data.id) {
                document.getElementById("firstName2").value = data.firstname;
                document.getElementById("lastName2").value = data.lastname;
                document.getElementById("age2").value = data.age;
            }
        })
    })
    $.getJSON("http://localhost:8080/address?pages=0",function(result){
        $.each(result.object.content,function (i ,data) {
            var address= "<option  value = '"+data.id+"' > "+ data.streetName +" "+data.code+"</option>"
            $("#addressId2").append(address)
        })
    })
    addressIdEdit=id;
}
$("#saveAddress2").click(function() {
    var name = $("#streetAddress2").val();
    var districtId2=$("#addressId2").val();
    var code = $("#codeAddress2").val();
    var districtDataEdit = {
        "streetName" : name,
        "districtId" : parseInt(districtId2),
        "code" : parseInt(code),
    }
    $.ajax('http://localhost:8080/address/edit/' + parseInt(addressIdEdit), {
        data: JSON.stringify(districtDataEdit),
        contentType: 'application/json',
        type: 'POST',
        dataType: 'json'})
});

//CLEAN
function  qoshish(){
    document.getElementById("streetNameAddress").value='';
    document.getElementById("codeAddress").value='';
}
function idUser(id){
    userId=id;
}