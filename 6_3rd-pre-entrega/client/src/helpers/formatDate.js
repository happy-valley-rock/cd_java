import moment from "moment";

const formatDate = {
  dateToQuery: (date, isFrom) => {
    let formatDate = date;
    if (typeof formatDate === 'object') {
      formatDate = formatDate.format();
    } else  {
      formatDate = moment(formatDate).format();
    }

    if (isFrom) {
      return `${formatDate}`;
    } else {
      return `${formatDate}`;
    }
  },
  setDateToDefaultValues: (date) => {
    let formatDate = date;
    if (typeof formatDate === 'object') {
      formatDate = formatDate.format('YYYY-MM-DD HH:mm')
    } else if (typeof formatDate === 'number') {
      formatDate = moment(new Date(formatDate)).format("YYYY-MM-DD HH:mm");
    }
    return formatDate;
  }
}

export default formatDate
