var regionId;
var regionIdEdit;
$(document).ready(function(){

    $("#qoshish").click(function (){
        qoshish();
    });
    $("#delet").click(function (){
        deleteUser(regionId);
    })
   //CREATE
    $("#save").click(function(){
var name= $("#name").val();
var code=  $("#code").val();
var my_data={
    "name": name,
    "code" : parseInt(code),
}
$.ajax('http://localhost:8080/region/upload' ,{
    data       : JSON.stringify(my_data),
    contentType: 'application/json',
    type       : 'POST',
    dataType   : 'json'
});
    });

    //READ
    var user=[];
        $.getJSON("http://localhost:8080/region/get?pages=0",function(result){
                   $.each(result.content,function (i ,data) {
                       var id=data.id;
                       var edit="<button  data-toggle=\"modal\" data-target=\"#myModal2\"  class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"2919592.svg\" onclick='editUser("+id+")'  ></button>"
                       var del=" <button  data-toggle=\"modal\" data-target=\"#myModal1\"   class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"del.png\" onclick='idYoz("+id+")' ></button> "
                    var tr_tag="<tr id='"+id+"'>"+
                           "<td>"+data.id+"</td>" +
                           "<td>"+ data.name + "</td>" +
                           "<td>"+ data.code + "</td>" +
                           "<td>"+ edit + "</td>" +
                           "<td>"+ del + "</td>"+"<tr>";

$('#us').append(tr_tag);
                   });
        });

});
//DELETE
function  deleteUser(regionId){
    $.getJSON('http://localhost:8080/region/delete/'+ regionId,function (result){});
}
//UPDATE
function  editUser(id)
{
    $.getJSON("http://localhost:8080/region/get?pages=0",function(result){
        $.each(result.content,function (i ,data){
if (id===data.id) {
    document.getElementById("name2").value = data.name;
    document.getElementById("code2").value = data.code;
}
        })

    })
    regionIdEdit=id;
}
    $("#save2").click(function() {
        var name = $("#name2").val();
        var code = $("#code2").val();
        var my_data1 = {
            "name" : name,
            "code" : parseInt(code),
        }
        $.ajax('http://localhost:8080/region/edit/' + parseInt(regionIdEdit), {
            data: JSON.stringify(my_data1),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json'})
        });



//CLEAN
function  qoshish(){
    document.getElementById("name").value='';
    document.getElementById("code").value='';
}
function idYoz(id){
    regionId=id;
}

//District CRUD

var districtId;
var districtIdEdit;
$(document).ready(function(){

    $("#addDistrict").click(function (){
        qoshish();
    });
    $("#deleteDist").click(function (){
        deleteDistrict(districtId);
    })
    //CREATE
    $("#saveDistrict").click(function(){
        var name= $("#nameDistrict").val();
        var code=  $("#codeDistrict").val();
        var regionId=$("#regionId").val();
        var district_data={
            "name": name,
            "code" : parseInt(code),
            "regionId" : parseInt(regionId),
        }
        $.ajax('http://localhost:8080/district' ,{
            data       : JSON.stringify(district_data),
            contentType: 'application/json',
            type       : 'POST',
            dataType   : 'json'
        });
    });

    //READ
    $.getJSON("http://localhost:8080/district?pages=0",function(result){
        $.each(result.object.content,function (i ,data) {
            console.log(data.name)
            var id=data.id;
            var edit="<button  data-toggle=\"modal\" data-target=\"#myModal2\"  class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"2919592.svg\" onclick='editDistrict("+id+")'  ></button>"
            var del=" <button  data-toggle=\"modal\" data-target=\"#myModal1\"   class='btn ' type=\"button\" id='"+id+"'><img width=\"30px\" height=\"20px\" src=\"del.png\" onclick='idDistrict("+id+")' ></button> "
            console.log(data.id);
            var tr_district="<tr id='"+id+"'>"+
                "<td>"+data.id+"</td>" +
                "<td>"+ data.name + "</td>" +
                "<td>"+ data.code + "</td>" +
                "<td>"+ data.region.name + "</td>" +
                "<td>"+ edit + "</td>" +
                "<td>"+ del + "</td>"+"<tr>";
            $('#district').append(tr_district);

        });
    });

    $.getJSON("http://localhost:8080/region/get?pages=0",function(result){
        $.each(result.content,function (i ,data) {
            var region= "<option  value = '"+data.id+"' > "+data.name+"</option>"
            $("#regionId").append(region)
        })
    })

});
//DELETE
function  deleteDistrict(idDel){
    $.getJSON('http://localhost:8080/district/delete/'+ parseInt(idDel),function (result){});
}
//UPDATE
function  editDistrict(id)
{
    $.getJSON("http://localhost:8080/district?pages=0",function(result){
        $.each(result.object.content,function (i ,data){
            if (id===data.id) {
                document.getElementById("nameDistrict2").value = data.name;
                document.getElementById("codeDistrict2").value = data.code;
            }
        })


    })
    $.getJSON("http://localhost:8080/region/get?pages=0",function(result){
        $.each(result.content,function (i ,data) {
            var region= "<option  value = '"+data.id+"' > "+data.name+"</option>"
            $("#regionId2").append(region)
        })
    })
    districtIdEdit=id;
}
$("#saveDistrict2").click(function() {
    var name = $("#nameDistrict2").val();
    var code = $("#codeDistrict2").val();
    var districtDataEdit = {
        "name" : name,
        "code" : parseInt(code),
        "regionId" : parseInt(regionIdEdit),
    }
    $.ajax('http://localhost:8080/region/edit/' + parseInt(regionIdEdit), {
        data: JSON.stringify(districtDataEdit),
        contentType: 'application/json',
        type: 'POST',
        dataType: 'json'})
});



//CLEAN
function  qoshish(){
    document.getElementById("nameDistrict").value='';
    document.getElementById("codeDistrict").value='';
}
function idDistrict(id){
    districtId=id;
}