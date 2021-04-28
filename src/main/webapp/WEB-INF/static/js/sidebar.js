$(function() {
	$("body").delegate(".search-icon", "click", function() {
		$(".search-result").empty();
		let categoryName = $("#search-word").val();
		if (categoryName !== null && categoryName !== '') {
			$.ajax({
				url: '/category/search',
				dataType: 'JSON',
				data: "categoryName=" + categoryName,
				success: function (result) {
					let $result = $('.search-result');
					for (const category of result.data) {
						let $tmp = $(`<li><a href=` + '/category/' + category.categoryId + `/>` + category.categoryName + `</a></li>`);
						$result.append($tmp);
					}
				}
			});
		}
	});

	$(".category-card-header").delegate("label","focus click",function () {
		$(".search-result").css('visibility', 'visible');
	}).delegate("li","blur",function () {
		$(".search-result").css('visibility', 'hidden');
	});

	$(window).scroll(function(event) {
		let winPos = $(window).scrollTop();
		if (winPos > 600) {
			$('.side-bar').addClass('fixed');
			$('.category-card').css('display', 'none');
		} else {
			$('.side-bar').removeClass('fixed');
			$('.category-card').css('display', 'block');
		}
	});
});
