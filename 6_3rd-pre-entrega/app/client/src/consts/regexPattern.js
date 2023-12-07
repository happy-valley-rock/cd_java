
export const REGEX = {
  USERNAME: /^(?=[a-zA-Z0-9._]{3,20}$)(?!.*[_.]{2})[^_.].*[^_.]$/,
  EMAIL: /^(([^<>()\]\\.,;:\s@"]+(\.[^<>()\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
  PASSWORD: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/,
  DATE: /^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$/,
  NAME: /^[ a-zA-ZÀ-ÿ\u00f1\u00d1]*$/,
  NUMBER: /^([0-9])*$/,
  PHONE: /^([0-9]|#|\ |\-|\+|\*)+$/,
  PRICE: /^([0-9]|\ |\,|\.)+$/
}
