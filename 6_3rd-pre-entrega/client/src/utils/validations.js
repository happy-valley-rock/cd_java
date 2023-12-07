
class Validations {
  payload = null;

  constructor() {
  }

  validPayload(payload) {
    this.payload = { ...payload };
    return this;
  }

  validEmptyBody(payload=this.payload) {
    if (!payload) return new Error('empty body');
    const { body } = payload;
    if (!body) return new Error('empty body');
    return this;
  }

  validEmptyQuery(payload=this.payload, keyWord) {
    const { query } = payload;
    if (!body) return new Error('empty body');
    return this;
  }

  createQueryParams(query){
    let queryParam = '?';
    for (const key in query) {
      if (query[key]) queryParam += `${key}=${query[key]}&`;
    }
    if (queryParam === '?') return '';
    return queryParam.slice(0, -1);
  }
}

export default Validations;
