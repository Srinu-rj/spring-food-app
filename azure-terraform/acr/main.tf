resource "azurerm_resource_group" "rg" {
  location = var.azure_location
  name     = var.resource_group_name
}

resource "azurerm_container_registry" "acr" {
  location            = var.azure_location
  name                = var.acr_name
  resource_group_name = var.resource_group_name
  sku                 = var.acr_sku
  admin_enabled       = false

  identity {
    type = "UserAssigned"
    identity_ids = [
      azurerm_user_assigned_identity.example.id
    ]
  }

  encryption {
    key_vault_key_id   = data.azurerm_key_vault_key.example.id
    identity_client_id = azurerm_user_assigned_identity.example.client_id
  }

}

resource "azurerm_user_assigned_identity" "example" {
  resource_group_name = azurerm_resource_group.example.name
  location            = azurerm_resource_group.example.location

  name = "registry-uai"
}

data "azurerm_key_vault_key" "example" {
  name         = "super-secret"
  key_vault_id = data.azurerm_key_vault.existing.id
}

