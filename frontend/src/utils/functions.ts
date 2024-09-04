import { toast } from "react-toastify";

interface IThenHandler {
  data: {
    message: string;
  };
}

interface ICatchHandler {
  response?: {
    data: {
      message: string;
    };
    status: number;
  };
}

export const catchHandler = (err: ICatchHandler) => {
  if (err.response?.data) {
    if (err.response.data.message) toast.error(err.response.data.message);
    else toast.error(`Erro: ${err.response.status}`);

    if (err.response.status === 401 && window.location.pathname !== "/login")
      window.location.pathname = "/login";
  } else {
    toast.error("Erro de comunicação");
  }
};

export const thenHandler = (res: IThenHandler) => {
  if (res.data) {
    toast.success(res.data.message);
  }
};

export const intlNumberFormatter = (number: number) => {
  return new Intl.NumberFormat("pt-br", {
    style: "currency",
    currency: "BRL",
  }).format(number);
};

export const valueMask = (value: string) => {
  const rawValue = value.replace(/\D/g, "");

  return (Number(rawValue) / 100).toLocaleString("pt-br", {
    style: "currency",
    currency: "BRL",
  });
};

export const unmaskValue = (value: string): number => {
  // Remove o símbolo da moeda, espaços, e substitui a vírgula por ponto
  const rawValue = value
    .replace(/[^\d,]/g, "") // Remove tudo que não for dígito ou vírgula
    .replace(/\.(?=.*\.)/, "") // Remove o primeiro ponto encontrado
    .replace(",", "."); // Substitui a vírgula decimal por ponto

  return parseFloat(rawValue) || 0;
};

export const DateFormater = (date: string) =>
  new Date(date).toLocaleDateString();
