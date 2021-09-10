document.addEventListener('DOMContentLoaded', function() {
	var currentLocale = $('#currentLocale')[0].textContent;
	$('#locales').val(currentLocale ? currentLocale : 'ru');

    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption) {
            window.location.replace('?lang=' + selectedOption);
            localStorage.setItem('locales', selectedOption);
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
	var currentPath = window.location.pathname;

	if (currentPath == '/') {
		$('#i18n')[0].style.display = 'block';
	} else {
		$('#i18n')[0].style.display = 'none';
	} 
});