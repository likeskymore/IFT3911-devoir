import Axios from 'axios'
import { BASE_URL } from './constants'

const httpClient = Axios.create({
  baseURL: BASE_URL,
  headers: {
      'X-Requested-With': 'XMLHttpRequest',
      'Content-Type': 'application/json',
      'Accept': 'application/json'
  },
  withCredentials: true,
  xsrfCookieName: 'XSRF-TOKEN',
  withXSRFToken: true,
})

export default httpClient