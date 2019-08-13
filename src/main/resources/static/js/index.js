$(function () {
	$('[data-toggle="tooltip"]').tooltip()
})

$(document).ready(function() {
	$('#lightSlider').lightSlider({
		item:4,
		loop:true,
		slideMove:1,
		easing: 'cubic-bezier(0.25, 0, 0.25, 1)',
		speed:600,
		responsive : [
		{
			breakpoint:800,
			settings: {
				item:1,
				slideMove:1,
				slideMargin:6,
			}
		},
		{
			breakpoint:480,
			settings: {
				item:1,
				slideMove:1
			}
		}
		]
	});
});