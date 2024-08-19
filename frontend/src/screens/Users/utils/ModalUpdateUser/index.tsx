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
import { useMutation, useQuery } from "@tanstack/react-query";
import { Controller, useForm } from "react-hook-form";
import { z } from "zod";
import { FormInput } from "../../../../components/Form/Input";
import { FormSelect } from "../../../../components/Form/Select";
import { AxiosClient } from "../../../../ServiceClients/AxiosClient";
import { PermissionService } from "../../../../services/permission";
import { unmaskValue, valueMask } from "../../../../utils/functions";
import { UserService } from "../../../../services/user";
import { IUser, TUserPost } from "../../../../utils/types";
import { toast } from "react-toastify";

interface IModalProps {
  onClose: () => void;
  isOpen: boolean;
  onSave: () => void;
  user: IUser;
}

export const ModalUpdateUser = ({
  isOpen,
  onClose,
  onSave,
  user,
}: IModalProps) => {
  const permissionService = new PermissionService(AxiosClient);
  const userService = new UserService(AxiosClient);

  const { data: permissionList } = useQuery({
    queryKey: ["permission"],
    queryFn: () => permissionService.getPermissionSelect(),
  });

  const mutation = useMutation({
    mutationFn: userService.update.bind(userService),
    onSuccess: () => {
      toast.success("Criado com sucesso!");
      onSave();
      onClose();
    },
  });

  const schema = z.object({
    name: z.string().min(3, "No minimo 3 caracteres"),
    email: z.string().email("Precisa ser um email").min(1, "Campo obrigatório"),
    workload: z.string().min(1, "Campo obrigatório"),
    wage: z.string().min(1, "Campo obrigatório"),
    role: z.string().min(1, "Campo obrigatório"),
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
      email: user.email,
      name: user.name,
      role: user.role,
      wage: valueMask(String(user.wage)),
      workload: user.workload,
    },
  });

  const handleClose = () => {
    reset();
    onClose();
  };

  const handleUpdate = (data: TFormData) => {
    const user: TUserPost = {
      ...data,
      wage: unmaskValue(data.wage),
    };

    mutation.mutate(user);
  };

  return (
    <Modal isOpen={isOpen} onClose={handleClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Editar Usuário</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <form onSubmit={handleSubmit(handleUpdate)}>
            <Flex direction="column" gap="10px">
              <FormInput
                label="Nome"
                {...register("name")}
                error={errors.name?.message}
                placeholder="Ex: josé da silva"
              />

              <FormInput
                label="Email"
                {...register("email")}
                error={errors.email?.message}
                placeholder="Ex: josesilva@exemplo.com"
              />

              <Controller
                name="wage"
                control={control}
                render={({ field }) => (
                  <FormInput
                    label="Salario"
                    {...field}
                    error={errors.wage?.message}
                    placeholder="Ex: 1.200,00"
                    onChange={(evt) => {
                      const formatedValue = valueMask(evt.target.value);

                      field.onChange(formatedValue);
                    }}
                  />
                )}
              />

              <FormInput
                label="Carga Horária"
                {...register("workload")}
                error={errors.workload?.message}
                placeholder="Ex: 8h"
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
                    {permissionList?.map(({ name, role }) => (
                      <option key={role} value={role}>
                        {name}
                      </option>
                    ))}
                  </FormSelect>
                )}
              />

              <Button type="submit" mb={5}>
                Editar
              </Button>
            </Flex>
          </form>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};
