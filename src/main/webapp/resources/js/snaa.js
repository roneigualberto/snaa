function configMaskData() {

	$(".campo-data input[type='text']").mask("99/99/9999");

}

function init() {
	
	$(".captcha").val('');


	configMaskData();
	configCalendar();


}	

function refreshCaptcha(id,url) {
	var token = new Date().getTime();
	document.getElementById(id).src=url+"?token="+token; 

}

function configCalendar() {
	configMaskData();

	PrimeFaces.locales['pt_BR'] = {
		closeText : 'Fechar',
		prevText : 'Anterior',
		nextText : 'Próximo',
		currentText : 'Começo',
		monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril',
				'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
				'Outubro', 'Novembro', 'Dezembro' ],
		monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
				'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
		dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
				'Sexta', 'Sábado' ],
		dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
				'Sáb' ],
		dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
		weekHeader : 'Semana',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : '',
		timeOnlyTitle : 'Somente Horas',
		timeText : 'Tempo',
		hourText : 'Hora',
		minuteText : 'Minuto',
		secondText : 'Segundo',
		currentText : 'Data Atual',
		ampm : false,
		month : 'Mês',
		week : 'Semana',
		day : 'Dia',
		allDayText : 'Todo Dia'
	};
}



$(document).ready(
		function() {

		    configCalendar();

		});
