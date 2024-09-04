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
import { z } from "zod";
import { FormInput } from "../../../../components/Form/Input";
import { FormSelect } from "../../../../components/Form/Select";
import { UserService } from "../../../../services/user";
import { permissionList } from "../../../../utils/constants";
import { IUser, TUserPut } from "../../../../utils/types";

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
  const userService = new UserService();

  const mutation = useMutation({
    mutationFn: (userUpdated: TUserPut) => userService.update(userUpdated),
    onSuccess: () => {
      onSave();
      onClose();
    },
  });

  const schema = z.object({
    name: z.string().min(3, "No minimo 3 caracteres"),
    username: z.string().min(1, "Campo obrigatório"),
    role: z.string().min(1, "Campo obrigatório"),
  });

  type TFormData = z.infer<typeof schema>;

  const {
    register,
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<TFormData>({
    resolver: zodResolver(schema),
    defaultValues: {
      username: user.username,
      name: user.name,
      role: user.role,
    },
  });

  const handleUpdate = (data: TFormData) => {
    mutation.mutate(data);
  };

  return (
    <Modal isOpen={isOpen} onClose={onClose}>
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
                label="Username"
                {...register("username")}
                error={errors.username?.message}
                placeholder="Ex: josesilva"
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
                    {permissionList?.map(({ label, value }) => (
                      <option key={value} value={value}>
                        {label}
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
