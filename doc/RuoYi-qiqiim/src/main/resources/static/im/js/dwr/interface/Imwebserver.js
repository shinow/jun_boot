if (typeof dwr == 'undefined' || dwr.engine == undefined) throw new Error('You must include DWR engine before including this file');

(function() {
  if (dwr.engine._getObject("Imwebserver") == undefined) {
    var p;
    
    p = {};

    /**
     * @param {function|Object} callback callback function or options object
     */
    p.serverconnect = function(callback) {
      return dwr.engine._execute(p._path, 'Imwebserver', 'serverconnect', arguments);
    };

    /**
     * @param {class java.lang.String} p0 a param
     * @param {class java.lang.String} p1 a param
     * @param {function|Object} callback callback function or options object
     */
    p.sendMsg = function(p0, p1, callback) {
      return dwr.engine._execute(p._path, 'Imwebserver', 'sendMsg', arguments);
    };

    /**
     * @param {function|Object} callback callback function or options object
     */
    p.closeconnect = function(callback) {
      return dwr.engine._execute(p._path, 'Imwebserver', 'closeconnect', arguments);
    };

    /**
     * @param {class java.lang.String} p0 a param
     * @param {class java.lang.String} p1 a param
     * @param {function|Object} callback callback function or options object
     */
    p.sendGroupMsg = function(p0, p1, callback) {
      return dwr.engine._execute(p._path, 'Imwebserver', 'sendGroupMsg', arguments);
    };
    
    dwr.engine._setObject("Imwebserver", p);
  }
})();

