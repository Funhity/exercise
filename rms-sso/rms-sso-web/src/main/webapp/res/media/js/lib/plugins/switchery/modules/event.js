define(function () {
    var bind = window.addEventListener ? 'addEventListener' : 'attachEvent',
        unbind = window.removeEventListener ? 'removeEventListener' : 'detachEvent',
        prefix = bind !== 'addEventListener' ? 'on' : '',
        exports = {};

    /**
     * Bind `el` event `type` to `fn`.
     *
     * @param {Element} el
     * @param {String} type
     * @param {Function} fn
     * @param {Boolean} capture
     * @return {Function}
     * @api public
     */

    exports.bind = function(el, type, fn, capture){
        el[bind](prefix + type, fn, capture || false);
        return fn;
    };

    /**
     * Unbind `el` event `type`'s callback `fn`.
     *
     * @param {Element} el
     * @param {String} type
     * @param {Function} fn
     * @param {Boolean} capture
     * @return {Function}
     * @api public
     */

    exports.unbind = function(el, type, fn, capture){
        el[unbind](prefix + type, fn, capture || false);
        return fn;
    };

    return exports;
})