import { css } from '@emotion/react';

export const SLayout =css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    & > input, button {
        height: 40px;
        width: 320px;
        font-size: 14px;
        margin: 20px 0px;
    }
`;
