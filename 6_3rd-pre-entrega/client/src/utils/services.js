
const setUpServicesHandler = ({
  postRequest,
  getRequest,
  putRequest,
  deleteRequest,
  axiosInstance
}) => {
  return {

    getClientList: function() {
      return getRequest('/api/client/list');
    },

    getProductList: function() {
      return getRequest('/api/product/list');
    },

    postInvoice: function(payload) {
      return postRequest('/api/invoice', payload);
    },

    getInvoiceTicket: function(invoiceId) {
      return getRequest(`/api/invoice/ticket/${invoiceId}`, {responseType: 'blob'});
    },

    getUrlInvoiceTicket: function(invoiceId) {
      return axiosInstance.defaults.baseURL + `/api/invoice/ticket/${invoiceId}`;
    }

  };
};

export default setUpServicesHandler;
