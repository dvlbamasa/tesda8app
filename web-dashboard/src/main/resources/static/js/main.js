function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

$(document).ready(function() {
    $('.double-scroll').doubleScroll();
    $('table').stickyTableHeaders({fixedOffset: $('.page-header')});
});