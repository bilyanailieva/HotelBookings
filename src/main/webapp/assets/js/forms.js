/**
 * 
 */
/* 
    $(document).ready(function() {
    $('#room_type').blur(function(event) {
            var room_type = $('#room_type').val();
            if(room_type == "double" || room_type == "Double" || room_type == "D") {
                room_type = $('#room_type').val("DBL");
            }
			if(room_type == "single" || room_type == "Single" || room_type == "S") {
				room_type = $('#room_type').val("SGL");
			}
            $.get('insertRoomType', {
                    room_type : room_type
            }, function(responseText) {
                    $('#insertMessage').text(responseText);
            });
    });
}); */

/* $(document).ready(function(){
   
    $("#room_type").blur(function(){
        
        var room_type =$("#room_type").val();
        if(room_type == "double" || room_type == "Double" || room_type == "D") {
            room_type = $('#room_type').val("DBL");
        }
        if(room_type == "single" || room_type == "Single" || room_type == "S") {
            room_type = $('#room_type').val("SGL");
        }
        if(room_type.length >= 2)
        {
            $.ajax({
                url:"../office/AjaxChecks/checkIfRoomTypeExists.jsp",
                type:"post",
                data:"room_type="+room_type,
                dataType:"text",
                success:function(data)
                {
                    $("#insertMessage").html(data)
                } 
            });
        }
        else
        {
          $("#insertMessage").html(" ");
        }
        
   }); 
   
});   */  


