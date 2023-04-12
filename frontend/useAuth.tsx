import { createContext, Dispatch, SetStateAction } from "react";
import SingleSignOnData from "./generated/dev/hilla/sso/starter/SingleSignOnData.js";

// Used for access control
export type AccessProps = Readonly<{
    requiresLogin?: boolean;
}>;

// The context type
export type Authentication = Readonly<{
    state: SingleSignOnData;
    hasAccess: (route: AccessProps) => boolean;
    clearAuthInfo: () => void;
}>;

// All necessary data is already loaded in the Hilla global variable
export const initialState = (window as any).Hilla.SSO as SingleSignOnData;

// The context itself
export const AuthContext = createContext<Authentication>({
  state: initialState,
  clearAuthInfo: () => { },
  hasAccess: function (route: Readonly<{ requiresLogin?: boolean | undefined; }>): boolean {
    throw new Error("Function not implemented.");
  }
});

// The hook to use the context
export const useAuth = (
    state: SingleSignOnData,
    setState: Dispatch<SetStateAction<SingleSignOnData>>
): Authentication => {
    return {
        state,
        hasAccess: (route: AccessProps) => {
            return !route.requiresLogin || state.authenticated;
        },
        clearAuthInfo: () => {
            setState({
                ...state,
                authenticated: false,
                backChannelLogoutEnabled: false,
                logoutLink: undefined,
                roles: [],
            });
        }
    }
};