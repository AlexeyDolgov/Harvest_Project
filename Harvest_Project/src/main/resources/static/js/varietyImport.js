messageResource.init({ filePath: '../../' });
var currentLocale = localStorage.getItem('locales');
messageResource.load('message', function() {}, currentLocale);

document.addEventListener('DOMContentLoaded', function() {
	$('#submit').click(function() {
		var errors = 0;

		var filenameError = validateOnEmptiness('filename', 'filenameError');
		errors += filenameError;
		
		if (filenameError == 0) {
			var filenameTypeError = validateOnFileType('filename', ['xls','xlsx'], 'filenameTypeError');
			errors += filenameTypeError;
		}
		
		var startRowError = validateOnEmptiness('startRow', 'startRowError');
		errors += startRowError;
		
		if (startRowError == 0) {
			var startRowIsNotNumberError = validateOnIsNumeric('startRow', 'startRowIsNotNumberError'); 
			errors += startRowIsNotNumberError;
		}
		
		var endRowError = validateOnEmptiness('endRow', 'endRowError');
		errors += endRowError;

		if (endRowError == 0) {
			var endRowIsNotNumberError = validateOnIsNumeric('endRow', 'endRowIsNotNumberError');
			errors += endRowIsNotNumberError;
		}
				
		if (startRowError == 0 && endRowError == 0 & startRowIsNotNumberError == 0 && endRowIsNotNumberError == 0) {
			errors += validateTwoFieldsOnLower('endRow', 'startRow', 'endRowLowerError');
		}
		
		var nameColumnError = validateOnEmptiness('columnFields[name]', 'nameColumnError');
		errors += nameColumnError;
		
		if (nameColumnError == 0) {
			var nameColumnIsNotNumberError = validateOnIsNumeric('columnFields[name]', 'nameColumnIsNotNumberError');
			errors += nameColumnIsNotNumberError;
		}
		
		if (errors != 0) {
			return false;
		} else {
			$('form').submit();
		}
	});
});