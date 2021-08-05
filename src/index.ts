import { registerPlugin } from '@capacitor/core';

import type { SingleSignOnPlugin } from './definitions';

const SingleSignOn = registerPlugin<SingleSignOnPlugin>('SingleSignOn');

export * from './definitions';
export { SingleSignOn };
