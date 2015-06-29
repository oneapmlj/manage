(function ($) {
    $.fn.extend({
        vscroller: function (options) {
            var settings = $.extend({ speed: 8000, stay: 5000, newsdata: '' }, options);

            return this.each(function () {
                var interval = null;
                var mouseIn = false;
                var totalElements;
                var isScrolling = false;
                var h;
                var t;
                var wrapper = $(this).addClass('news-wrapper');
                if (settings.newsdata == '') {  return; }
				var newsdata=settings.newsdata;
				var contentWrapper = $('<div/>').addClass('news-contents-wrapper');
				var newsHeader = $('<div/>').addClass('news-header');
				var newsContents = $('<div/>').addClass('news-contents');
				wrapper.append(contentWrapper);
				contentWrapper.append(newsHeader);
				contentWrapper.append(newsContents);
				var detail_title = $('<div/>').addClass('detail_title');
				var date_title = $('<div/>').addClass('date_title');
				newsHeader.append(detail_title);
				newsHeader.append(date_title);
				detail_title.html("签单细节");
				date_title.html("签单日期");

				for(var j=0;j<newsdata.length;j++){
						var news = $('<div/>').addClass('news');
						newsContents.append(news);
						var detail = $('<div/>').addClass('detail');
						var date = $('<div/>').addClass('date');
						news.append(detail);
						news.append(date);
						detail.html(newsdata[j].detail);
						date.html(newsdata[j].date);
				}
				var i = 0;
				totalElements = newsdata.length;
				h = parseFloat($('.news:eq(0)').outerHeight());
				$('.news', wrapper).each(function () {
					$(this).css({ top: i++ * h });
				});
				t = (totalElements - 1) * h;
				interval = setTimeout(scroll, 1);
                   
                
                function scroll() {
					isScrolling = true;
					$('.news:eq(0)').stop(true, false).animate({ top: -h }, settings.speed, function () {

						clearTimeout(interval);
						var current = $('.news:eq(0)').clone(true);
						current.css({ top: t });
						$('.news-contents').append(current);
						$('.news:eq(0)').remove();
						isScrolling = false;
						interval = setTimeout(scroll, settings.stay);

					});
                    $('.news:gt(0)').stop(true, false).animate({ top: '-=' + h }, settings.speed);
                   
                }

            });
        }

    });
})(jQuery);
