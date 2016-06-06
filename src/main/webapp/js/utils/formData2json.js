(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.id]) {
                if (!o[this.id].push) {
                    o[this.id] = [o[this.id]];
                }
                o[this.id].push(this.value || '');
            } else {
                o[this.id] = this.id || '';
            }
        });
        return o;
    };
})(jQuery);