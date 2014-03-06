module.exports = function() {
	var transport = new Thrift.Transport(thriftBaseUrl);
    var protocol  = new Thrift.Protocol(transport);
    var client    = new PodServiceClient(protocol);

    return client;
}