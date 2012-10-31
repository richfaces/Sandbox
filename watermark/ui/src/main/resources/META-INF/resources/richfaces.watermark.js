(function ($, rf) {

    rf.ui = rf.ui || {};

    var defaultOptions = {};

    var inputLocator = "input[type=text], input[type=password], textarea";

    rf.ui.Watermark = rf.BaseComponent.extendClass({

        name:"Watermark",

        init: function (componentId, options) {
            $super.constructor.call(this, componentId);
            this.attachToDom(this.id);
            $(function() {
                options.className = options['styleClass'];
                var elements = (options.selector) ? $(options.selector) : $(document.getElementById(options.targetId));
                // finds all inputs within the subtree of target elements
                var inputs = elements
                    .find(inputLocator)
                    .andSelf()
                    .filter(inputLocator);
                inputs.watermark(options.text, options);
            });
        },
        // destructor definition
        destroy: function () {
            // define destructor if additional cleaning is needed but
            // in most cases its not nessesary.
            // call parent’s destructor
            $super.destroy.call(this);
        }
    });
    // define super class reference - reference to the parent prototype
    var $super = rf.ui.Watermark.$super;
})(jQuery, RichFaces);