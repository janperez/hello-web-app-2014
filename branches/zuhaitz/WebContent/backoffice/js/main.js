$(document).ready(function() {

	$('#personasList').dataTable({
		"aoColumns" : [ {
			"bSearchable" : false
		}, null, null, null, null ]
	});

	$('#calificacionesList').dataTable({
		"aoColumns" : [ {
			"bSearchable" : false
		}, null, null, null ]
	});

});