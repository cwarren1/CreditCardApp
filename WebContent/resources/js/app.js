window.fn = {};

window.fn.open = function() {
  var menu = document.getElementById('menu');
  menu.open();
};

window.fn.load = function(page) {
  var content = document.getElementById('content');
  var menu = document.getElementById('menu');
  content.load(page)
    .then(menu.close.bind(menu));
};

function onError(tx, e){
	alert("There was an error " + e.Message);
}

function onSuccess(tx, r){
	
}
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}
function freezeCard(){	
	var freeze = 	document.querySelector('ons-switch').checked;
	var acctId = document.querySelector('ons-select').value;
	//var freezeAcctURL =  getContextPath() +"/FreezeAccount?freeze=" + freeze+"&acctId=" + acctId;

	$.ajax({
		type : "POST",
	
		url : getContextPath() +"/FreezeAccount",		
		data: { freeze : JSON.stringify(freeze),
				acctId : JSON.stringify(acctId)
		},
		dataType : 'json',
		
		success : function(data) {
			console.log("SUCCESS: ", data);
			 ons.notification.alert('You account was updated.');
		},
		error : function(e) {
			console.log("ERROR: ", e);
			
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}	
	function replaceCard(){	
		var reason = document.querySelector('ons-radio').value;
		var acctId = document.querySelector('ons-select').value;
		var comments = document.querySelector('ons-input').value;

		$.ajax({
			type : "POST",
		
			url : getContextPath() +"/ReplaceCard",		
			data: { reason : JSON.stringify(reason),
					acctId : JSON.stringify(acctId),
					comments : JSON.stringify(comments)
			},
			dataType : 'json',
			
			success : function(data) {
				console.log("SUCCESS: ", data);
				 ons.notification.alert('You request was received.');
			},
			error : function(e) {
				console.log("ERROR: ", e);
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}
