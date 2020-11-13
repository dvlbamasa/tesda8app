$(document).ready(function() {
    $('.double-scroll').doubleScroll();
    $('table').stickyTableHeaders({fixedOffset: $('.page-header')});
});

$('.nav-pills').find('button').on('show.bs.tab', function () {
    // Some code you want to run when tab is clicked (before the tab is shown)
    $('.double-scroll').doubleScroll();
});

$('.nav-pills').find('button').on('shown.bs.tab', function () {
    // Some code you want to run after the tab is shown (callback)
    $('.double-scroll').doubleScroll();
});