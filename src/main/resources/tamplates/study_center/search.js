
var userId;
var addressIdEdit;
$(document).ready(function(){
    //GET REGION
    $.getJSON("http://localhost:8080/region/get?pages=0",function(result){
        $.each(result.content,function (i ,data) {
            var region= "<option  value = '"+data.id+"' > "+data.name+"</option>"
            $("#regionId").append(region)
        });

    });
    //GET DISTRICT
    $.getJSON("http://localhost:8080/district?pages=0",function(result){
        $.each(result.object.content,function (i ,data) {
            var district= "<option  value = '"+data.id+"' > "+data.name+"</option>"
            $("#districtId").append(district)

        });
    });
    //GET COMPANY
    $.getJSON("http://localhost:8080/region/get?pages=0",function(result){
        $.each(result.content,function (i ,data) {
            var region= "<option  value = '"+data.id+"' > "+data.name+"</option>"
            $("#regionId").append(region)
        })
    });//GET CATEGORY
    $.getJSON("http://localhost:8080/courseCategory",function(result){
        $.each(result,function (i ,data) {
            var category= "<option  value = '"+data.id+"' > "+data.name+"</option>"
            console.log(data.name)
            $("#categoryId").append(category)
        })
    });
    //COURSE
    $.getJSON("http://localhost:8080/course",function(result){
        $.each(result,function (i ,data) {
            var course= "<option  value = '"+data.id+"' > "+data.name+"</option>"
            $("#courseId").append(course)
        })
    });
})
//SEARCH TUGMASI BOSILGANIDA
function searchCourse(){
    $(document).ready(function(){
        $("#course").toggleClass("visibility: visible");
if ($("#regionId").val()!="---"){

    $.getJSON("http://localhost:8080/course",function(result){
        $.each(result,function (i ,data) {
            console.log(data.name)
            var id=data.id;

            console.log(data.id);
            var tr_course=
                "<tr id='"+id+"'>"+
                "<td>"+data.id+"</td>" +
                "<td>"+ data.name + "</td>"+
                "<td>"+ data.description + "</td>"+
                "<td>"+ data.company.name + "</td>"+
                "<td>"+ data.com + "</td>"+
                "<td>"+ data.name + "</td>"

            $('#courseTable').append(tr_course);
        });
    });

}









    })
}
