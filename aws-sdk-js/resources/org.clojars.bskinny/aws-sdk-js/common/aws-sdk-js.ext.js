/**********************************************************************
 * Minimal externs for AWS S3 and Cognito
 **********************************************************************/
var AWS = {
  "S3": {
    "getObject": function () {},
    "putObject": function () {},
    "copyObject": function () {},
    "deleteObjects": function () {},
    "getSignedUrl": function () {}
  },
  "config": {
    "region": function () {},
    "credentials": function () {}
  }
};
AWS.CognitoIdentityCredentials.prototype = {
  "cacheId": function () {},
  "clearCachedId": function () {},
  "clearIdOnNotAuthorized": function () {},
  "coalesceRefresh": function () {},
  "constructor": function () {},
  "createClients": function () {},
  "expiryWindow": function () {},
  "get": function () {},
  "getCredentialsForIdentity": function () {},
  "getCredentialsFromSTS": function () {},
  "getId": function () {},
  "getPromise": function () {},
  "getStorage": function () {},
	"identityId": {},
  "load": function () {},
  "loadCachedId": function () {},
  "loadCredentials": function () {},
  "localStorageKey": function () {},
  "needsRefresh": function () {},
  "refresh": function () {},
  "refreshPromise": function () {},
  "setStorage": function () {},
  "storage": function () {}
};
