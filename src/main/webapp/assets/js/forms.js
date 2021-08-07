/**
 * 
 */

    $(document).ready(function() {
    $('#room_type').blur(function(event) {
            var room_type = $('#room_type').val();
            if(room_type == "double" || room_type == "Double" || room_type == "D") {
                room_type = $('#room_type').val("DBL");
            }
			if(room_type == "single" || room_type == "Single" || room_type == "S") {
				room_type = $('#room_type').val("SGL");
			}
            $.get('roomtypes', {
                    room_type : room_type
            }, function(responseText) {
                    $('#insertMessage').text(responseText);
            });
    });
});


