

export default function formatErrorResponse(error) {
  console.error(error);
  if (error.response) {
    return { status: error.response.status, error: error.response.data };
  } else {
    return { status: 500, error: 'unknown error' };
  }
}
