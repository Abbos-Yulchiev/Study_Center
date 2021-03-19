
var contactId;
var addressIdEdit;
$(document).ready(function(){



    //CREATE
    $("#saveContact").click(function(){
        var phone= $("#phoneNumber").val();
        var email=  $("#email").val();
        var webPage=$("#webPage").val();
        var telegramAddress=$("#telegramAddress").val();
        var addressId=$("#addressId").val();
        var contact_data={
            "phoneNumber": phone,
            "email" : email,
            "webPage" : webPage,
            "telegramAddress" : telegramAddress,
            "addressId" : addressId,
        }
        $.ajax('http://localhost:8080/contact' ,{
            data       : JSON.stringify(contact_data),
            contentType: 'application/json',
            type       : 'POST',
            dataType   : 'json'
        });
    });

    //READ
    $.getJSON("http://localhost:8080/contact?pages=0",function(result){
        $.each(result.object.content,function (i ,data) {
            console.log(data.name)
            var id=data.id;
            var edit="<button  data-toggle=\"modal\" data-target=\"#myModal2\"  class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"2919592.svg\" onclick='editContact("+id+")'  ></button>"
            var del=" <button  data-toggle=\"modal\" data-target=\"#myModal1\"   class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"del.png\" onclick='idContact("+id+")' ></button> "
            console.log(data.id);
            var tr_address="<tr id='"+id+"'>"+
                "<td>"+data.id+"</td>" +
                "<td>"+ data.phoneNumber + "</td>" +
                "<td>"+ data.email + "</td>" +
                "<td>"+ data.webPage + "</td>" +
                "<td>"+ data.telegramAddress + "</td>" +
                "<td>"+ edit + "</td>" +
                "<td>"+ del + "</td>"+"<tr>";
            $('#contact').append(tr_address);

        });
    });




    $("#addAddress").click(function (){
        qoshish();
    });
    $("#deleteContact").click(function (){
        deleteAddress(contactId);
    })

});
//DELETE
function  deleteAddress(idDelit){
    $.getJSON('http://localhost:8080/contact/delete/'+ parseInt(idDelit),function (result){});
}
//UPDATE
function  editContact(id)
{
    $.getJSON("http://localhost:8080/contact?pages=0",function(result){
        $.each(result.object.content,function (i ,data){
            if (id===data.id) {
                document.getElementById("phoneNumber2").value = data.phoneNumber;
                document.getElementById("email2").value = data.email;
                document.getElementById("webPage2").value = data.webPage;
                document.getElementById("telegramAddress2").value = data.telegramAddress;
            }
        })
    })
    contactIdEdit=id;
}
$("#saveContact2").click(function() {
    var phone= $("#phoneNumber2").val();
    var email=  $("#email2").val();
    var webPage=$("#webPage2").val();
    var telegramAddress=$("#telegramAddress2").val();
    var contact_dataEdit={
        "phoneNumber": phone,
        "email" : email,
        "webPage" : webPage,
        "telegramAddress" : telegramAddress,
    }
    $.ajax('http://localhost:8080/contact/'+contactIdEdit ,{
        data       : JSON.stringify(contact_dataEdit),
        contentType: 'application/json',
        type       : 'POST',
        dataType   : 'json'
    });
});

//CLEAN
function  qoshish(){
    document.getElementById("streetNameAddress").value='';
    document.getElementById("codeAddress").value='';
}
function idContact(id){
    contactId=id;
}