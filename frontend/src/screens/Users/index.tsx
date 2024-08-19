import {
  Box,
  Button,
  Flex,
  Select,
  Text,
  useDisclosure,
} from "@chakra-ui/react";
import { Pencil, PlusCircle } from "@phosphor-icons/react";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useState } from "react";
import { toast } from "react-toastify";
import { IconButton } from "../../components/IconButton";
import { PopoverDelete } from "../../components/Popover";
import { InfoTable, InfoTableContent } from "../../components/Table";
import { Title } from "../../components/Text/Title";
import { AxiosClient } from "../../ServiceClients/AxiosClient";
import { PermissionService } from "../../services/permission";
import { UserService } from "../../services/user";
import { DateFormater, intlNumberFormatter } from "../../utils/functions";
import { ModalCreateUser } from "./utils/ModalCreateUser";
import { IUser } from "../../utils/types";
import { ModalUpdateUser } from "./utils/ModalUpdateUser";

export const UserList = () => {
  // const userService = new UserService(AxiosClient);
  // const permissionService = new PermissionService(AxiosClient);
  const { isOpen, onClose, onOpen } = useDisclosure();
  // const {
  //   isOpen: updateIsOpen,
  //   onClose: updateClose,
  //   onOpen: updateOnOpen,
  // } = useDisclosure();
  // const [selectedUser, setSelectedUser] = useState<IUser>();

  // const [selectedPermission, setSelectedPermission] = useState("");

  // const {
  //   data: userList,
  //   isLoading,
  //   refetch,
  // } = useQuery({
  //   queryKey: ["users"],
  //   queryFn: () => userService.getUserList({ role: selectedPermission }),
  // });

  // const { data: permissionList } = useQuery({
  //   queryKey: ["permission"],
  //   queryFn: () => permissionService.getPermissionSelect(),
  // });

  // const mutation = useMutation({
  //   mutationFn: (id: string) => userService.delete(id),
  //   onSuccess: () => {
  //     toast.success("Apagado com sucesso!");
  //     refetch();
  //   },
  // });

  return (
    <>
      {/* {updateIsOpen && selectedUser && (
        <ModalUpdateUser
          isOpen={updateIsOpen}
          onClose={updateClose}
          onSave={() => ""}
          user={selectedUser}
        />
      )} */}
      {<ModalCreateUser isOpen={isOpen} onClose={onClose} onSave={() => ""} />}
      <Flex justify="space-between">
        <Title label="Usuários" />
        <IconButton
          icon={<PlusCircle size={26} />}
          label="Cadastrar"
          onClick={onOpen}
        />
      </Flex>
      {/* <Box bgColor="#f9f9f988" borderRadius={5} p={5} mt={5}>
        <Text as="h4" fontSize="xl">
          Filtros
        </Text>
        <Flex mt={5} gap={5}>
          <Select
            width="300px"
            bgColor="white"
            onChange={(evt) => setSelectedPermission(evt.target.value)}
            placeholder="Selecione a permissão"
          >
            <option value="">Todas</option>
            {permissionList?.map(({ name, role }) => (
              <option key={role} value={role}>
                {name}
              </option>
            ))}
          </Select>
          <Button onClick={() => refetch()}>Filtrar</Button>
        </Flex>
      </Box> */}
      {/* {!isLoading && userList && (
        <Box minWidth="100%" pt={10}>
          {userList.length > 0 && (
            <InfoTable
              headProps={[
                { label: "Nome" },
                { label: "Email" },
                { label: "Salario" },
                { label: "Permissão" },
                { label: "Carga Horária" },
                { label: "Criação" },
                { label: "Ações" },
              ]}
            >
              {userList.map((user) => (
                <InfoTableContent
                  key={user.id}
                  colsBody={[
                    { ceil: user.name },
                    { ceil: user.email },
                    { ceil: intlNumberFormatter(user.wage) },
                    { ceil: user.role },
                    { ceil: user.workload },
                    { ceil: DateFormater(user.createdAt) },
                    {
                      ceil: (
                        <Flex>
                          <PopoverDelete
                            section="Usuário"
                            onClick={() => mutation.mutate(user.id)}
                          />
                          <Button
                            variant="none"
                            onClick={() => {
                              console.log(user);
                              setSelectedUser(user);
                              updateOnOpen();
                            }}
                          >
                            <Pencil size={22} />
                          </Button>
                        </Flex>
                      ),
                    },
                  ]}
                />
              ))}
            </InfoTable>
          )}
        </Box> */}
      {/* )} */}
    </>
  );
};
