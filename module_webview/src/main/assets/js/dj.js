var dj = {};

dj.os = {};

dj.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);

dj.os.isAndroid = !dj.os.isIOS;

dj.callbackName = function() {
	return "djAPI_callback_" + (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
};

dj.callbacks = {};

dj.addCallback = function(name, func, userData) {
	delete dj.callbacks[name];
	dj.callbacks[name] = {
		callback: func,
		userData: userData
	};
};

dj.postWithCallback = function(cmd, param, callback, userData) {
	var callbackName = dj.callbackName();
	dj.addCallback(callbackName, callback, userData);
	if (window.dj.os.isAndroid) {
		param.callback = callbackName;
		window.JsBridge.handleWebAction(cmd, JSON.stringify(param));
	} else if (dj.os.isIOS) {
		var message = {};
		message.meta = {
			cmd: cmd,
			callback: callbackName
		};
		message.param = param;
		window.JsBridge.handleWebAction(message);
	}
};

// native调用
dj.callback = function(param) {
	console.log(param.toString());
	var callbackObject = dj.callbacks[param.callbackName];
	if (callbackObject !== undefined) {
		if (callbackObject.userData !== undefined) {
			callbackObject.userData.callbackData = param;
		}
		if (callbackObject.callback != undefined) {
			var ret = callbackObject.callback(param, callbackObject.userData);
			if (ret === false) {
				return
			}
			delete dj.callbacks[param.callbackName];
		}
	}
};

dj.post = function(cmd, param) {
	if (window.dj.os.isAndroid) {
		window.JsBridge.handleWebAction(cmd, JSON.stringify(param));
	} else if (dj.os.isIOS) {
		var message = {};
		message.meta = {
			cmd: cmd
		};
		message.param = param || {};
		window.JsBridge.handleWebAction(message);
	}
};

dj.dispatchEvent = function(param) {
	if (!param) {
		param = {
			"name": "webViewLoadComplete"
		};
	}
	var evt = {};
	try {
		evt = new Event(param.name);
		evt.param = param.param;
	} catch (e) {
		evt = document.createEvent("HTMLEvents");
		evt.initEvent(param.name, false, false);
	}
	window.dispatchEvent(evt);
};

dj.addEventListener = window.addEventListener;

dj.stringify = function(obj) {
	var type = typeof obj;
	if (type == "object") {
		return JSON.stringify(obj);
	} else {
		return obj;
	}
};

window.dj = dj;
