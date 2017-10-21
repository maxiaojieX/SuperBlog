$(function(){
	var mo = $("#notifymessage");
	$.post("/admin/notify").done(function(json){
		if(json.json > 0){
			mo.text(json.json);
		}
    });
	//轮询
	setInterval(function(){
        $.post("/admin/notify").done(function(json){
        	if(json.json > 0){
    			mo.text(json.json);
    		}
        });
    },3000);
	
});