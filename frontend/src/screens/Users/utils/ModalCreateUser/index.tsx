import {
  Button,
  Flex,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalHeader,
  ModalOverlay,
} from "@chakra-ui/react";
import { zodResolver } from "@hookform/resolvers/zod";
import { useMutation } from "@tanstack/react-query";
import { Controller, useForm } from "react-hook-form";
import { toast } from "react-toastify";
import { z } from "zod";
import { FormInput } from "../../../../components/Form/Input";
import { FormSelect } from "../../../../components/Form/Select";
import { AxiosClient } from "../../../../ServiceClients/AxiosClient";
import { UserService } from "../../../../services/user";
import { TUserPost } from "../../../../utils/types";

interface IModalProps {
  onClose: () => void;
  isOpen: boolean;
  onSave: () => void;
}

const permissionList = [
  { label: "Aluno", value: "ALUNO" },
  { label: "Professor", value: "PROFESSOR" },
  { label: "Admin", value: "ADMIN" },
];

export const ModalCreateUser = ({ isOpen, onClose, onSave }: IModalProps) => {
  const userService = new UserService(AxiosClient);

  const mutation = useMutation({
    mutationFn: userService.post.bind(userService),
    onSuccess: () => {
      toast.success("Criado com sucesso!");
      onSave();
      onClose();
    },
  });

  const schema = z
    .object({
      name: z.string().min(3, "No minimo 3 caracteres"),
      username: z.string().min(3, "No minimo 3 caracteres"),
      password: z
        .string()
        .min(6, "No minimo 6 caracteres")
        .regex(/\d/, "A senha deve conter pelo menos um número")
        .regex(/[a-z]/, "A senha deve conter pelo menos uma letra minúscula"),
      confirmPassword: z.string(),
      role: z.string().min(1, "Campo obrigatório"),
    })
    .refine((data) => data.password === data.confirmPassword, {
      message: "As senhas devem ser iguais",
      path: ["confirmPassword"],
    });

  type TFormData = z.infer<typeof schema>;

  const {
    register,
    control,
    reset,
    handleSubmit,
    formState: { errors },
  } = useForm<TFormData>({
    resolver: zodResolver(schema),
    defaultValues: {
      name: "",
      password: "",
      role: "",
      username: "",
      confirmPassword: "",
    },
  });

  const handleClose = () => {
    reset();
    onClose();
  };

  const handleCreate = (data: TFormData) => {
    console.log(data);

    const user: TUserPost = {
      ...data,
    };
    mutation.mutate(user);
  };

  return (
    <Modal isOpen={isOpen} onClose={handleClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Cadastrar Usuário</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <form onSubmit={handleSubmit(handleCreate)}>
            <Flex direction="column" gap="10px">
              <FormInput
                label="Nome"
                {...register("name")}
                error={errors.name?.message}
                placeholder="Ex: josé da silva"
              />

              <FormInput
                label="Username"
                {...register("username")}
                error={errors.username?.message}
                placeholder="Ex: luizObrabo"
              />

              <Controller
                name="role"
                control={control}
                render={({ field }) => (
                  <FormSelect
                    label="Permissões"
                    error={errors.role?.message}
                    {...field}
                  >
                    <option value="">Selecione</option>
                    {permissionList.map(({ label, value }) => (
                      <option key={value} value={value}>
                        {label}
                      </option>
                    ))}
                  </FormSelect>
                )}
              />

              <FormInput
                label="Senha"
                {...register("password")}
                error={errors.password?.message}
                placeholder="No minimo 6 digitos"
                type="password"
              />

              <FormInput
                label="Confirme a senha"
                {...register("confirmPassword")}
                error={errors.confirmPassword?.message}
                placeholder="Confirme sua senha"
                type="password"
              />

              <Button type="submit" mb={5}>
                Cadastrar
              </Button>
            </Flex>
          </form>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};
