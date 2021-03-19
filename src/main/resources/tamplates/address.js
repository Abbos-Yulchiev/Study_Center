
var addressId;
var addressIdEdit;
$(document).ready(function(){
    $("#addAddress").click(function (){
        qoshish();
    });
    $("#deleteAddress").click(function (){
        deleteAddress(addressId);
    })


    //CREATE
    $("#saveAddress").click(function(){
        var name= $("#streetNameAddress").val();
        var code=  $("#codeAddress").val();
        var districtId=$("#addressId").val();
        var address_data={
            "streetName": name,
            "districtId" : parseInt(addressId),
            "code" : parseInt(code),
        }
        $.ajax('http://localhost:8080/address' ,{
            data       : JSON.stringify(address_data),
            contentType: 'application/json',
            type       : 'POST',
            dataType   : 'json'
        });
    });

    //READ
    $.getJSON("http://localhost:8080/address?pages=0",function(result){
        $.each(result.object.content,function (i ,data) {
            console.log(data.name)
            var id=data.id;
            var edit="<button  data-toggle=\"modal\" data-target=\"#myModal2\"  class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"2919592.svg\" onclick='editAddress("+id+")'  ></button>"
            var del=" <button  data-toggle=\"modal\" data-target=\"#myModal1\"   class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"del.png\" onclick='idAddress("+id+")' ></button> "
            console.log(data.id);
            var tr_address="<tr id='"+id+"'>"+
                "<td>"+data.id+"</td>" +
                "<td>"+ data.streetName + "</td>" +
                "<td>"+ data.code + "</td>" +
                "<td>"+ data.district.name + "</td>" +
                "<td>"+ data.district.region.name + "</td>" +
                "<td>"+ edit + "</td>" +
                "<td>"+ del + "</td>"+"<tr>";
            $('#address').append(tr_address);

        });
    });

    $.getJSON("http://localhost:8080/address/get?pages=0",function(result){
        $.each(result.object.content,function (i ,data) {
            var address= "<option  value = '"+data.id+"' > "+data.name+"</option>"
            $("#addressIdId").append(address)
        })
    })

});
//DELETE
function  deleteAddress(idDeli){
    $.getJSON('http://localhost:8080/address/delete/'+ parseInt(idDeli),function (result){});
}
//UPDATE
function  editAddress(id)
{
    $.getJSON("http://localhost:8080/address?pages=0",function(result){
        $.each(result.object.content,function (i ,data){
            if (id===data.id) {
                document.getElementById("streetAddress2").value = data.streetName;
                document.getElementById("codeAddress2").value = data.code;
            }
        })
    })
    $.getJSON("http://localhost:8080/district?pages=0",function(result){
        $.each(result.object.content,function (i ,data) {
            var address= "<option  value = '"+data.id+"' > "+data.name+"</option>"
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
function idAddress(id){
    addressId=id;
}