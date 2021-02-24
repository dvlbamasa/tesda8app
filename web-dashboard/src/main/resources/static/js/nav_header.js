$(document).ready(function(){
    $(".navDropdown").hover(function(){
        var dropdownMenu = $(this).children(".dropdown-menu");
        if(dropdownMenu.is(":visible")){
            dropdownMenu.parent().toggleClass("open");
        }
    });

    var activeLink = $('li.active');
    if (activeLink.attr('id') != 'active-pagenumber') {
        activeLink.removeClass('active');
    }
    $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
});