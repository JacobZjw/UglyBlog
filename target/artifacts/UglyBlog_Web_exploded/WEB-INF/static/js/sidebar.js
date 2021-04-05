$(function() {
	$("body").delegate(".search-icon", "click", function() {
		// $(".search-result").hide().empty();
		$(".search-result").hide();

		$("#search-word").val("");
		/**使用AJAX向服务端查询**/
	});
	$('#search-word').focus(function() {
		$(".search-result").css('display', 'block');
	}).blur(function() {
		$(".search-result").css('display', 'none');
	});

	$(window).scroll(function(event) {
		var winPos = $(window).scrollTop();
		if (winPos > 600) {
			$('.side-bar').addClass('fixed');
			$('.category-card').css('display', 'none');
		} else {
			$('.side-bar').removeClass('fixed');
			$('.category-card').css('display', 'block');
		}
	});
});
