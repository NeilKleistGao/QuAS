module.exports = {
    isPC: function() {
        let agent = navigator.userAgent;
        let agents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"];
        let flag = true;

        for (let ag of agents) {
            if (agent.indexOf(ag) > 0) {
                flag = false;
                break;
            }
        }

        return flag;
    }
};