import moment from 'moment'
/**
 * format 格式化时间样式
 */
export function formatDate(value, format) {
    var data = moment(value).format(format);
    return data;
}